import Lea.cards.*;
import Lea.cards.arts.T1.SphereSaw;
import Lea.cards.words.Wait;
import Lea.patches.AbstractCardEnum;
import Lea.relics.SpheromancerHeart;
import basemod.BaseMod;
import basemod.abstracts.CustomCard;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.localization.RelicStrings;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.unlock.UnlockTracker;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class LeaMod implements CharacterMod{

    public static final Color COBALT = CardHelper.getColor(45.0f, 27.0f, 175.0f);  //A voir si on met 45.0f, 27.0f, 175.0f plutôt mais ça sera pas du COBALT
    private static final String ATTACK = "bg_attack_purple";
    private static final String SKILL = "bg_skill_purple";
    private static final String POWER = "bg_power_purple";
    private static final String ENERGY_ORB = "card_purple_orb";
    private static final String SMALL_ENERGY_ORB = "small_orb";

    public LeaMod(){
        BaseMod.addColor(AbstractCardEnum.LEA_COBALT, COBALT, COBALT, COBALT, COBALT, COBALT,
            COBALT, COBALT, makeCardBgSmallPath(ATTACK), makeCardBgSmallPath(SKILL),
            makeCardBgSmallPath(POWER), makeCardBgSmallPath(ENERGY_ORB),
            makeCardBgLargePath(ATTACK), makeCardBgLargePath(SKILL),
            makeCardBgLargePath(POWER), makeCardBgLargePath(ENERGY_ORB), makeCardBgSmallPath(SMALL_ENERGY_ORB));
    }





    /**Code repris du mod FruityMod qui a créé le Seeker et le Tranquil **/
    /**
     * Makes a full path for a small card background
     * @param cardType the card type, must *NOT* have a leading "/"
     * @return the full path to the card background
     */
    public static final String makeCardBgSmallPath(String cardType) {
        return makePath("512/" + cardType);
    }

    /**
     * Makes a full path for a large card background
     * @param cardType the card type, must *NOT* have a leading "/"
     * @return the full path to the card background
     */
    public static final String makeCardBgLargePath(String cardType) {
        return makePath("1024/" + cardType);
    }

    /**
     * Makes a full path for a card image
     * @param cardName the resource, must *NOT* have a leading "/"
     * @return the full path to the card image
     */
    public static final String makeCardImagePath(String cardName) {
        return makePath("cards/" + cardName);
    }

    public static final String makeRelicImagePath(String relicName) {
        return makePath("relics/" + relicName);
    }

    /**
     * Makes a full path for a character image
     * @param image the image, must *NOT* have a leading "/"
     * @return the full path to the character image
     */
    public static final String makeCharacterImagePath(String image) {
        return makePath("char/" + image);
    }

    /**
     * Makes a full path for a resource path
     * @param resource the resource, must *NOT* have a leading "/"
     * @return the full path
     */
    private static final String makePath(String resource) {
        String result = "img/Lea/" + resource;

        result += ".png";

        return result;
    }

    /**
     * Makes a full path for a power path
     * @param power the resource, must *NOT* have a leading "/"
     * @return the full path
     */
    public static final String makePowerImagePath(String power) {
        return makePath("powers/" + power.replaceFirst("Lea_", ""));
    }

    //Contient la liste des cartes moddés à rajouter dans le pool de cartes jouables
    @Override
    public void receiveEditCards() {
        List<CustomCard> cards = new ArrayList<CustomCard>();
        LeaCrosscode.logger.info("Ajout de RIEN---------------------------------------------------------------------------------------------------------------------------------\n\n");

        cards.add(new Hit());
        cards.add(new Dash());
        cards.add(new SphereSaw());
        cards.add(new HitAndBlock());
        cards.add(new TripleHits());
        cards.add(new Wait());
        cards.add(new VPRCharge());
        cards.add(new ShockJolt());
        for(CustomCard card : cards) {

            LeaCrosscode.logger.info("Ajout de "+card.cardID + "---------------------------------------------------------------------------------------------------------------------------------\n\n");
            BaseMod.addCard(card);
            UnlockTracker.unlockCard(card.cardID);
        }


    }

    @Override
    public void receiveEditCharacters() {
        //J'le fais dans LeaCrosscode à voir pour restructurer comme le reste quand y'aura plusieurs persos.
    }

    @Override
    public void receiveEditKeywords() {
        BaseMod.addKeyword(new String[] {"protection", "protections", "prots"}, "Reduce by 2 + this amount damage taken this turn.");
        BaseMod.addKeyword(new String[] {"chill", "chills","chilled", "Chill", "Chills","Chilled"}, "DESCRIPTION A AJOUTER");
        BaseMod.addKeyword(new String[] {"burn", "burns", "burned", "Burn", "Burns", "Burned"}, "DESCRIPTION A AJOUTER");
        BaseMod.addKeyword(new String[] {"jolt", "jolts", "jolted", "Jolt", "Jolts", "Jolted"}, "DESCRIPTION A AJOUTER");
        BaseMod.addKeyword(new String[] {"mark", "marks", "marked", "Mark", "Marks", "Marked"}, "DESCRIPTION A AJOUTER");
    }

    @Override
    public void receiveEditRelics() {
        //BaseMod.addRelicToCustomPool(new SpheromancerHeart(), AbstractCardEnum.LEA_COBALT);
    }

    @Override
    public void receiveEditStrings() {
        // RelicStrings
        String relicStrings = Gdx.files.internal("localization/Lea-RelicStrings.json").readString(String.valueOf(StandardCharsets.UTF_8));
        BaseMod.loadCustomStrings(RelicStrings.class, relicStrings);
        // CardStrings
        String cardStrings = Gdx.files.internal("localization/Lea-CardStrings.json").readString(String.valueOf(StandardCharsets.UTF_8));
        BaseMod.loadCustomStrings(CardStrings.class, cardStrings);

        LeaCrosscode.logger.info("done editting strings");
    }

    @Override
    public void receiveCardUsed(AbstractCard abstractCard) {

    }

    @Override
    public void receivePowersModified() {

    }

    @Override
    public void receivePostBattle(AbstractRoom abstractRoom) {

    }

    @Override
    public void receivePostDraw(AbstractCard abstractCard) {

    }

    @Override
    public void receivePostDungeonInitialize() {

    }

    @Override
    public void receivePostExhaust(AbstractCard abstractCard) {

    }
}
