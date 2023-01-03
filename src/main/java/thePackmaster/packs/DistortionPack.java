package thePackmaster.packs;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.UIStrings;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.ThePackmaster;
import thePackmaster.actions.distortionpack.ImproveAction;
import thePackmaster.cards.dimensiongatepack.*;
import thePackmaster.cards.distortionpack.*;

import java.util.ArrayList;
import java.util.List;

import static thePackmaster.SpireAnniversary5Mod.modID;

public class DistortionPack extends AbstractCardPack {
    public static final String ID = SpireAnniversary5Mod.makeID("DistortionPack");
    private static final UIStrings UI_STRINGS = CardCrawlGame.languagePack.getUIString(ID);
    public static final String NAME = UI_STRINGS.TEXT[0];
    public static final String DESC = UI_STRINGS.TEXT[1];
    public static final String AUTHOR = UI_STRINGS.TEXT[2];

    public DistortionPack() {
        super(ID, NAME, DESC, AUTHOR);
    }

    @Override
    public ArrayList<String> getCards() {
        ArrayList<String> cards = new ArrayList<>();
        cards.add(S_r_ke.ID);
        cards.add(BurningAct.ID);
        cards.add(CursedCircle.ID);
        cards.add(Darken.ID);
        cards.add(Deconstruct.ID);
        cards.add(Deterioration.ID);
        cards.add(MindMaze.ID);
        cards.add(Rue.ID);
        cards.add(Shatter.ID);
        cards.add(Static.ID);
        return cards;
    }

    public void initializePack() {
        for (String s : getCards()) {
            AbstractCard c = CardLibrary.getCard(s);
            SpireAnniversary5Mod.cardParentMap.put(c.cardID, packID);
            cards.add(c.makeStatEquivalentCopy());
        }
        previewPackCard = new DistortionPackPreview(packID, this);
    }

    private static class DistortionPackPreview extends CardPackPreview {
        private static Texture distortionPackTexture = null;

        public DistortionPackPreview(String cardID, AbstractCardPack parentPack) {
            super(cardID, parentPack);

            if (distortionPackTexture == null) {
                List<String> skills = new ArrayList<>();
                for (AbstractCard c : CardLibrary.getAllCards()) {
                    if (c.color == ThePackmaster.Enums.PACKMASTER_RAINBOW && c.type == CardType.SKILL)
                        skills.add(c.cardID);
                }
                String imgID = skills.get(MathUtils.random(skills.size() - 1));
                this.textureImg = getCardTextureString(imgID.replace(modID + ":", ""), CardType.SKILL);
                Texture cardTexture;
                if (imgMap.containsKey(textureImg)) {
                    cardTexture = imgMap.get(textureImg);
                } else {
                    cardTexture = ImageMaster.loadImage(textureImg);
                    if (cardTexture != null) {
                        cardTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
                        imgMap.put(textureImg, cardTexture);
                    }
                    else {
                        imgID = S_r_ke.ID;
                        this.textureImg = getCardTextureString(imgID.replace(modID + ":", ""), CardType.SKILL);
                        if (imgMap.containsKey(textureImg)) {
                            cardTexture = imgMap.get(textureImg);
                        } else {
                            cardTexture = ImageMaster.loadImage(textureImg);
                            cardTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
                            imgMap.put(textureImg, cardTexture);
                        }
                    }
                }

                distortionPackTexture = ImproveAction._refactor(cardTexture, false);
            }

            int tw = distortionPackTexture.getWidth();
            int th = distortionPackTexture.getHeight();
            this.portrait = this.jokePortrait = new TextureAtlas.AtlasRegion(distortionPackTexture, 0, 0, tw, th);
        }
    }
}
