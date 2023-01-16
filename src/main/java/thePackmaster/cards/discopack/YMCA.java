package thePackmaster.cards.discopack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import thePackmaster.actions.discopack.SpecificToHandFromDiscardAction;
import thePackmaster.powers.eurogamepack.TotalTrackerPower;


import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.atb;
import static thePackmaster.util.Wiz.att;

public class YMCA extends AbstractSmoothCard{
    public static final String ID = makeID("YMCA");

    public int discardCount = 0;
    public YMCA() {
        super(ID, 2, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY);
        this.baseSecondMagic = secondMagic = discardCount;
        this.baseDamage = damage = 2;
    }


    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.calculateCardDamage(m);
        for(int i = 0; i < this.discardCount; ++i) {
            dmg(m, AbstractGameAction.AttackEffect.SMASH);
        }
    }
    public void applyPowers() {
        AbstractPower strength = AbstractDungeon.player.getPower("Strength");
        if (strength != null) {
            strength.amount *= 0.5;
        }
        super.applyPowers();
        if (strength != null) {
            strength.amount /= 0.5;
        }
    }

    public void calculateCardDamage(AbstractMonster mo) {
        AbstractPower strength = AbstractDungeon.player.getPower("Strength");
        if (strength != null) {
            strength.amount *= 0.5;
        }
        super.calculateCardDamage(mo);
        if (strength != null) {
            strength.amount /= 0.5;
        }
    }

    public void triggerOnManualDiscard() {
        discardCount = discardCount + 1;
        baseSecondMagic = discardCount;
        att(new SpecificToHandFromDiscardAction(this));
    }
    @Override
    public void upp() {
        this.selfRetain = true;
    }
}


