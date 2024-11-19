package thePackmaster.cards.farmerpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class Hydroponics extends AbstractFarmerCard {
    public final static String ID = makeID("Hydroponics");
    public Hydroponics() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = damage = 4;
        baseMagicNumber = magicNumber = 0;
    }

public void use(AbstractPlayer p, AbstractMonster m) {
    int count = checkAttack(true);
    //dmg(m, AbstractGameAction.AttackEffect.POISON);
    for (int j = 0; j < count; j++) {
        dmg(m, AbstractGameAction.AttackEffect.POISON);
    }
}
public void calculateCardDamage(AbstractMonster mo) {
    super.calculateCardDamage(mo);
    this.rawDescription = cardStrings.DESCRIPTION;
    modDesc();
    this.initializeDescription();
}
public void applyPowers() {
    int count = checkAttack(false);
    //if (count > 0) {
        this.baseMagicNumber = count;
        super.applyPowers();
        modDesc();
        this.initializeDescription();
    //}
}
public void onMoveToDiscard() {
    this.rawDescription = cardStrings.DESCRIPTION;
    this.initializeDescription();
}
    public void triggerWhenDrawn() {
        this.rawDescription = cardStrings.DESCRIPTION;
        this.initializeDescription();
    }
private void modDesc(){this.rawDescription = cardStrings.DESCRIPTION + cardStrings.EXTENDED_DESCRIPTION[0];}
    public int checkAttack(boolean cardIsBeingPlayed){
        int numToCheck = Math.max(0, AbstractDungeon.actionManager.cardsPlayedThisTurn.size() - (cardIsBeingPlayed ? 1 : 0));
        return (int)AbstractDungeon.actionManager.cardsPlayedThisTurn.subList(0, numToCheck).stream()
                .map(c -> c.type)
                .filter(c -> c != CardType.ATTACK)
                .count();
    }

    public void upp() {
        upgradeDamage(1);
        //this.retain = true;
    }
}
