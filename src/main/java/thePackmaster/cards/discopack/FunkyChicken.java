package thePackmaster.cards.discopack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.actions.discopack.DiscardBasicGainAction;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.atb;

public class FunkyChicken extends AbstractSmoothCard {
    public static final String ID = makeID("FunkyChicken");

    public FunkyChicken() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        this.baseDamage = this.damage = 6;
        this.baseSecondMagic = secondMagic = 2;
        this.baseMagicNumber = magicNumber = 0;
    }
    public String text = "Discard up to " + secondMagic + " card(s).";
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {

        this.addToBot(new DiscardBasicGainAction(p, "damage", secondMagic, text, m, this.damage));


    }

    public void triggerOnManualDiscard() {
        this.addToBot(new DrawCardAction(AbstractDungeon.player, secondMagic));
    }

    @Override
    public void upp() {
        this.upgradeDamage(3);
        this.upgradeSecondMagic(1);
    }
}