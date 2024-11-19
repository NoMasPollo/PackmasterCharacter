package thePackmaster.cards.farmerpack;

import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.red.Inflame;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.SpireAnniversary5Mod;

import java.util.ArrayList;
import java.util.Objects;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.atb;
import static thePackmaster.util.Wiz.getRandomItem;

public class GeneticallyModified extends AbstractFarmerCard {
    public final static String ID = makeID("GeneticallyModified");
    private static final String SCREEN_MSG = CardCrawlGame.languagePack.getUIString(SpireAnniversary5Mod.makeID("GeneticallyModified")).TEXT[0];
    public GeneticallyModified() {
        super(ID, 1, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY);
        baseDamage = damage = 9;
        baseMagicNumber = magicNumber = 3;
        this.exhaust = true;
    }
ArrayList<AbstractCard> powerList;
    public void upp() {
        this.exhaust = false;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.BLUNT_HEAVY);
        if(powerList == null || powerList.isEmpty()){
            findViable();
        }
        generatePower();
    }
    public void findViable(){
        powerList = new ArrayList<>();
        for (AbstractCard C : AbstractDungeon.srcUncommonCardPool.group)
        {
            if (C.color == AbstractDungeon.player.getCardColor()
                    && (C.rarity == CardRarity.UNCOMMON)
                    && (!C.hasTag(CardTags.HEALING))
                    && (C.type == CardType.POWER)){
                    powerList.add(C);}
        }
    }
    public AbstractCard getRandom() {
        if(powerList != null && !powerList.isEmpty()){
            return getRandomItem(powerList);
        }
        return new Inflame();
    }
    public void generatePower(){
        ArrayList<AbstractCard> generatedCards = new ArrayList<>();
        while (generatedCards.size() < magicNumber) {
            AbstractCard tmp = getRandom();
            boolean dupe = false;
            for (AbstractCard c : generatedCards) {
                if (c.cardID.equals(tmp.cardID)) {
                    dupe = true;
                    break;
                }
            }
            if (!dupe) {
                generatedCards.add(tmp.makeCopy());
                //if (upgraded){
                //    for (int i=0; i < timesUpgraded; i++){
                //        tmp.upgrade();
                //    }
                //}
            }
        }
        atb(new SelectCardsAction(generatedCards, 1, SCREEN_MSG, false, Objects::nonNull,
                (cards) -> {
                    for (AbstractCard c2 : cards) {
                        c2.setCostForTurn(c2.cost - 1);
                        atb(new MakeTempCardInHandAction(c2.makeStatEquivalentCopy()));
                    }
                }
        ));
    }
}

