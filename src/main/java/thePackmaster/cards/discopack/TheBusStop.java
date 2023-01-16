package thePackmaster.cards.discopack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.atb;

public class TheBusStop extends AbstractSmoothCard{
    public static final String ID = makeID("TheBusStop");
    public TheBusStop() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        this.baseBlock = this.block = 7;
        this.baseMagicNumber = magicNumber = 1;
    }



    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new GainBlockAction(AbstractDungeon.player, AbstractDungeon.player, block));
        this.addToBot(new DiscardAction(p, p, 1, false));
    }
    public void triggerOnManualDiscard() {
        this.addToTop(new GainBlockAction(AbstractDungeon.player, AbstractDungeon.player, block + magicNumber));
    }
    @Override
    public void upp() {
    this.upgradeBlock(4);
    this.upgradeMagicNumber(1);
    }
}
