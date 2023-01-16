package thePackmaster.cards.discopack;

import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.atb;

public class DanceTransition extends AbstractSmoothCard{
    public static final String ID = makeID("DanceTransition");

    public int oldHandSize = 0;
    public DanceTransition() {
        super(ID, 1, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        this.baseMagicNumber = magicNumber = 0;
        this.baseSecondMagic = secondMagic = 10;
        this.exhaust = true;
    }


    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        oldHandSize = p.gameHandSize;
        atb(new DrawCardAction(p, 10));
        atb(new DiscardAction(p, p, p.gameHandSize, true));
        atb(new DrawCardAction(p, oldHandSize + magicNumber));
    }
    @Override
    public void upp() {
        this.upgradeMagicNumber(1);
        this.selfRetain = true;
    }
}

