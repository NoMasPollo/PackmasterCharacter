package thePackmaster.cards.discopack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;


import static thePackmaster.SpireAnniversary5Mod.makeID;

public class TheRobot extends AbstractSmoothCard{
    public static final String ID = makeID("TheRobot");
    public CardType detectedType = CardType.STATUS;
    public AbstractGameAction action;
    public TheRobot() {
        super(ID, 4, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        this.baseMagicNumber = magicNumber = 1;
        this.isEthereal = true;
    }


    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.applyToSelf(new StrengthPower(AbstractDungeon.player, magicNumber));
        this.applyToSelf(new DexterityPower(AbstractDungeon.player, magicNumber));
        this.cost = 4;
    }
    public void triggerOnManualDiscard() {
        if (this.cost >  0) {
            this.cost = this.cost - 1;
        }
    }
    @Override
    public void upp() {
        this.upgradeMagicNumber(1);
    }
}
