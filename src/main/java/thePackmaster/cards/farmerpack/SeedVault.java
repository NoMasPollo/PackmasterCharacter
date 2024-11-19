package thePackmaster.cards.farmerpack;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.powers.farmerpack.SeedVaultPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.applyToSelf;

public class SeedVault extends AbstractFarmerCard {
    public final static String ID = makeID("SeedVault");

    public SeedVault() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 1;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new SeedVaultPower(p, magicNumber, magicNumber));
    }


    public void upp() {
        upgradeBaseCost(0);
    }
}
