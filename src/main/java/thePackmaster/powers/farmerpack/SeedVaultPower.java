//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package thePackmaster.powers.farmerpack;

import basemod.cardmods.ExhaustMod;
import basemod.helpers.CardModifierManager;
import basemod.interfaces.CloneablePowerInterface;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.NonStackablePower;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.AbstractCard.CardType;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import thePackmaster.powers.AbstractPackmasterPower;

import java.util.ArrayList;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.atb;

public class SeedVaultPower extends AbstractPackmasterPower implements CloneablePowerInterface, NonStackablePower {
    public static final String POWER_ID = makeID("SeedVaultPower");
    private static final String NAME = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).NAME;
    private static final String[] DESCRIPTIONS = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).DESCRIPTIONS;

    public SeedVaultPower(AbstractCreature owner, int amount, int amount2) {
        super(POWER_ID, NAME, PowerType.BUFF, false, owner, amount);
        this.isTwoAmount = true;
        this.amount2 = amount2;
        this.updateDescription();
    }
    private ArrayList<AbstractCard> CardsToAdd = new ArrayList<>();

    public String descriptors(String desc){
        if(this.CardsToAdd != null && !this.CardsToAdd.isEmpty()) {
            if (Settings.language == Settings.GameLanguage.ENG) {
                int i = 0;
                for (AbstractCard card : CardsToAdd) {
                    if (CardsToAdd.size() == 1) {
                        desc += CardCrawlGame.languagePack.getCardStrings(card.cardID).NAME + ".";
                    } else if (i++ == CardsToAdd.size() - 1) {
                        desc += "and " + CardCrawlGame.languagePack.getCardStrings(card.cardID).NAME + ".";
                    } else {
                        desc +=  CardCrawlGame.languagePack.getCardStrings(card.cardID).NAME + ", ";
                    }
                }
            }
            else{
            for (AbstractCard card : CardsToAdd) {
                desc += CardCrawlGame.languagePack.getCardStrings(card.cardID).NAME + " ";
            }}
        }
        return desc;
    }
    public void updateDescription() {
        String fulldesc;
        if (this.amount2 == 1) {
            fulldesc = DESCRIPTIONS[0];
        } else {
            fulldesc = DESCRIPTIONS[1] + this.amount2 + DESCRIPTIONS[2];
        }
        fulldesc = descriptors(fulldesc);
        description = fulldesc;
    }

    @Override
    public void onAfterCardPlayed(AbstractCard usedCard) {
        super.onAfterCardPlayed(usedCard);
        if (!usedCard.purgeOnUse && (usedCard.type == CardType.ATTACK || usedCard.type == CardType.SKILL) && this.amount > 0 && this.amount2 > 0) {
            AbstractCard modify = usedCard.makeStatEquivalentCopy();
            //modify.timesUpgraded = 0;
            CardModifierManager.addModifier(modify, new ExhaustMod());
            //for (int i = 0; i < usedCard.timesUpgraded; i++){modify.upgrade();}
            modify.modifyCostForCombat(-modify.cost);
            CardsToAdd.add(modify);
            this.amount2 -= 1;
            flash();
        }
        updateDescription();
    }

    @Override
    public void atStartOfTurn() {
        for (AbstractCard card: CardsToAdd) {
            atb(new MakeTempCardInHandAction(card));
        }
        flash();
            removeThis();
    }

    @Override
    public boolean isStackable(AbstractPower power){
        if (power instanceof SeedVaultPower) {
            amount2 += ((SeedVaultPower) power).amount2;
            ((SeedVaultPower) power).amount2 = amount2;
            //amount -= 1;
            this.updateDescription();
            return true;
        }
        return false;
    }

    @Override
    public AbstractPower makeCopy() {
        return new SeedVaultPower(owner, amount, amount2);
    }
}
