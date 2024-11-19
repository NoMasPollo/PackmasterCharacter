package thePackmaster.powers.farmerpack;


import basemod.interfaces.CloneablePowerInterface;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.NonStackablePower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.vfx.combat.CleaveEffect;
import thePackmaster.powers.AbstractPackmasterPower;

import java.util.HashSet;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class OutToMarketPower extends AbstractPackmasterPower  implements CloneablePowerInterface, NonStackablePower {
    public static final String POWER_ID = makeID("OutToMarketPower");
    private static final String NAME = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).NAME;
    private static final String[] DESCRIPTIONS = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).DESCRIPTIONS;
    private String desc;

    private HashSet<AbstractCard.CardType> played = new HashSet<>();

    public OutToMarketPower(AbstractCreature owner, int amount, int amount2) {
        super(POWER_ID, NAME, PowerType.BUFF, false, owner, amount);
        this.isTwoAmount = true;
        this.amount2 = amount2;
        updateDescription();
    }

    @Override
    public void onInitialApplication() {
        cleanUp();
    }
    @Override
    public boolean isStackable(AbstractPower power){
        if (power instanceof OutToMarketPower) {
            amount2 += ((OutToMarketPower) power).amount2;
            ((OutToMarketPower) power).amount2 = amount2;
            return true;
        }
        return false;
    }
    @Override
    public void atStartOfTurn() {
        cleanUp();
    }

    public void onAfterCardPlayed(AbstractCard card) {
        if(!played.contains(card.type)) {
            played.add(card.type);

            flash();
            addToBot(new SFXAction("ATTACK_HEAVY"));
            if (Settings.FAST_MODE) {
                addToBot(new VFXAction(new CleaveEffect()));
            } else {
                addToBot(new VFXAction(this.owner, new CleaveEffect(), 0.2F));
            }
            updateDescription();
            addToBot(new DamageAllEnemiesAction(this.owner, DamageInfo.createDamageMatrix(this.amount, true), DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.NONE, true));
            addToBot(new GainBlockAction(this.owner, amount2));
        }
    }

    public void updateDescription() {
        desc = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1] + this.amount2 + DESCRIPTIONS[2];
        this.description = descriptors(desc);
    }

    private void cleanUp() {
        played.clear();
        desc = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1] + this.amount2 + DESCRIPTIONS[2];
        this.description = desc;
    }

    private String updateCardsPlayed(AbstractCard.CardType type) {
        String add;
        switch (type) {
            case SKILL:
                add = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).DESCRIPTIONS[4];
                break;
            case ATTACK:
                add = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).DESCRIPTIONS[3];
                break;
            case POWER:
                add = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).DESCRIPTIONS[5];
                break;
            case STATUS:
                add = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).DESCRIPTIONS[6];
                break;
            case CURSE:
                add = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).DESCRIPTIONS[7];
                break;
            default:
                add = type.name();
        }
        return add;
    }
    public String descriptors(String descs){
        if(this.played != null && !this.played.isEmpty()){
        if(Settings.language == Settings.GameLanguage.ENG){

            int i = 0;
            for (AbstractCard.CardType type: played) {
                if(played.size() == 1){descs +=  updateCardsPlayed(type) + ".";}
                else if(i++ == played.size() - 1){descs += "and " + updateCardsPlayed(type) + ".";}
                else {descs += updateCardsPlayed(type) + ", ";}
            }
        }
        else{
            for (AbstractCard.CardType type: played) {
                descs +=  updateCardsPlayed(type) + " ";
        }}}
        return descs;
    }
    @Override
    public AbstractPower makeCopy() {
        return new OutToMarketPower(owner, amount, amount2);
    }
}
