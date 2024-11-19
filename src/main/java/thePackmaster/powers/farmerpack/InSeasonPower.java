package thePackmaster.powers.farmerpack;

import basemod.interfaces.CloneablePowerInterface;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.NonStackablePower;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.PlatedArmorPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import thePackmaster.powers.AbstractPackmasterPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.applyToSelf;

public class InSeasonPower extends AbstractPackmasterPower  implements CloneablePowerInterface, NonStackablePower {
    public static final String POWER_ID = makeID("InSeasonPower");
    private static final String NAME = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).NAME;
    private static final String[] DESCRIPTIONS = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).DESCRIPTIONS;

    public InSeasonPower(AbstractCreature owner, int amount, int amount2) {
        super(POWER_ID, NAME, PowerType.BUFF, false, owner, amount);
        this.isTwoAmount = true;
        this.amount2 = amount2;
        updateDescription();
    }
    public void onAfterCardPlayed(AbstractCard card) {
        if(card.type  != AbstractCard.CardType.ATTACK){
            applyToSelf(new StrengthPower(owner, amount));
        }
        if (card.type  == AbstractCard.CardType.ATTACK){
            applyToSelf(new PlatedArmorPower(owner, amount2));
        }
    }

    @Override
    public void atStartOfTurn() {
        this.flash();
        removeThis();
    }

    public void updateDescription() {
this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1] + amount2 + DESCRIPTIONS[2];
    }
    @Override
    public boolean isStackable(AbstractPower power){
        if (power instanceof InSeasonPower) {
            amount2 += ((InSeasonPower) power).amount2;
            ((InSeasonPower) power).amount2 = amount2;
            return true;
        }
        return false;
    }
    @Override
    public AbstractPower makeCopy() {
        return new InSeasonPower(owner, amount, amount2);
    }
}
