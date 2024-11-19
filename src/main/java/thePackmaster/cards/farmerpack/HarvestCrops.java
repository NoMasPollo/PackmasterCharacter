package thePackmaster.cards.farmerpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardQueueItem;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.actions.ChangePlayedCardExhaustAction;
import thePackmaster.util.Wiz;

import java.util.ArrayList;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.atb;

public class HarvestCrops extends AbstractFarmerCard {
    public final static String ID = makeID("HarvestCrops");
    //private AbstractCard theLastNAP;

    public HarvestCrops() {
        super(ID, 2, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = damage = 9;
        this.exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.SLASH_VERTICAL);
        if (lastNAP(2) != null){
            AbstractCard tmp = cardsToPreview;
            AbstractDungeon.player.limbo.addToBottom(tmp);
            tmp.current_x = this.current_x;
            tmp.current_y = this.current_y;
            tmp.target_x = (float) Settings.WIDTH / 2.0F - 300.0F * Settings.scale;
            tmp.target_y = (float)Settings.HEIGHT / 2.0F;
            if (m != null) {tmp.calculateCardDamage(m);}
            tmp.purgeOnUse = true;
            AbstractDungeon.actionManager.addCardQueueItem(new CardQueueItem(tmp, m, tmp.energyOnUse, true, true), true);
            if(tmp.type != CardType.POWER){Wiz.atb(new ChangePlayedCardExhaustAction(this, false));}
        }
    }
    public void applyPowers() {
        this.cardsToPreview = lastNAP(1);
        super.applyPowers();
    }
    public void upp() {
        upgradeDamage(3);
    }

    private ArrayList<AbstractCard> ptt() {return AbstractDungeon.actionManager.cardsPlayedThisTurn;}
    private AbstractCard lastNAP(int played){
        //for (int i = ptt().size() - 1; i >= 0; i--) {
        //    if (ptt().get(i).type != CardType.ATTACK){
        //        AbstractCard tmp = ptt().get(i).makeStatEquivalentCopy();
        //        return tmp;
        //    }
        //}
        if (!ptt().isEmpty() && ptt().get(ptt().size() - played).type != CardType.ATTACK){
            AbstractCard tmp = ptt().get(ptt().size() - played).makeStatEquivalentCopy();
            return tmp;
        }
        return null;
    }
}
