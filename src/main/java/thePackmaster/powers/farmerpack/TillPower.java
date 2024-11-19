package thePackmaster.powers.farmerpack;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import thePackmaster.cards.farmerpack.Fertilizer;
import thePackmaster.powers.AbstractPackmasterPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.atb;

public class TillPower extends AbstractPackmasterPower{
    public static final String POWER_ID = makeID("TillPower");
    private static final String NAME = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).NAME;
    private static final String[] DESCRIPTIONS = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).DESCRIPTIONS;

    public TillPower(AbstractCreature owner, int amount) {
        super(POWER_ID, NAME, PowerType.BUFF, false, owner, amount);
    }
    @Override
    public void atStartOfTurn() {
        this.flash();
        for (int i=0; i < amount; i++) {atb(new MakeTempCardInHandAction(new Fertilizer()));}
        removeThis();
    }

    public void updateDescription() {
        if(amount == 1){this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];}
        else {this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[2];}

    }
}
