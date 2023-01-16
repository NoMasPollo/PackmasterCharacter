package thePackmaster.cards.discopack;


import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.actions.discopack.DiscardBasicGainAction;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class ElectricSlide extends AbstractSmoothCard {
    public static final String ID = makeID("ElectricSlide");

    public ElectricSlide() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        this.baseBlock = this.block = 6;
        this.baseMagicNumber = magicNumber = 0;
        this.baseSecondMagic = secondMagic = 2;
    }
    public String text = "Discard up to " + secondMagic + " card(s).";

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new DiscardBasicGainAction(p, "block", secondMagic, text, m, this.block));
    }

    public void triggerOnManualDiscard() {
        this.addToBot(new DrawCardAction(AbstractDungeon.player, secondMagic));
    }

    @Override
    public void upp() {
        this.upgradeBlock(3);
        this.upgradeSecondMagic(1);
    }
}
