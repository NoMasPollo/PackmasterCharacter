package thePackmaster.cards.farmerpack;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.powers.farmerpack.InSeasonPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.atb;

public class InSeason extends AbstractFarmerCard {
    public final static String ID = makeID("InSeason");

    public InSeason() {
        super(ID, 1, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        baseMagicNumber = magicNumber = 1;
        baseSecondMagic = secondMagic = 2;
        this.exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new ApplyPowerAction(p, p, new InSeasonPower(p, magicNumber, secondMagic)));
    }

    public void upp() {
        //upgradeBaseCost(0);
        this.retain = true;
    }
}
