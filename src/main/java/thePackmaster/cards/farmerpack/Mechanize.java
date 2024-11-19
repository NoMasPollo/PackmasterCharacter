package thePackmaster.cards.farmerpack;

import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.HashSet;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.atb;

public class Mechanize extends AbstractFarmerCard {
    public final static String ID = makeID("Mechanize");

    public Mechanize() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 1;
        this.exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        HashSet<CardType> types = new HashSet<>();
        for (AbstractCard c2c:p.hand.group) {
            if (c2c != this && !types.contains(c2c.type)){
                types.add(c2c.type);
                atb(new GainEnergyAction(magicNumber));
            }
        }

    }

    public void upp() {
        this.exhaust = false;
    }
}
