package thePackmaster.cards.discopack;

import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.unique.DiscardPileToTopOfDeckAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;


import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.atb;
import static thePackmaster.util.Wiz.att;

public class PointMove extends AbstractSmoothCard{
    public static final String ID = makeID("PointMove");


    public PointMove() {
        super(ID, 0, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        this.baseMagicNumber = magicNumber = 1;
    }


    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new DiscardPileToTopOfDeckAction(p));
        atb(new DrawCardAction(p, 1));
    }
    public void triggerOnManualDiscard() {
        att(new GainEnergyAction(magicNumber));
    }
    @Override
    public void upp() {
        this.upgradeMagicNumber(1);
    }
}

