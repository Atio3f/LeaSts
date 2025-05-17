package Lea;

import Lea.cards.*;
import Lea.cards.arts.T1.FrozenStance;
import Lea.cards.arts.T2.AmberBreaker;
import Lea.cards.arts.T2.VolcanoCrash;
import Lea.cards.arts.T3.OneMoreTime;
import Lea.cards.arts.starter.BulletRain;
import Lea.cards.arts.starter.SphereSaw;
import Lea.cards.companions.Ctron;
import Lea.cards.companions.Emilienator;
import Lea.cards.companions.Hackgey;
import Lea.cards.consumables.BoltDrink;
import Lea.cards.consumables.Durian;
import Lea.cards.curses.MemoryLost;
import Lea.cards.curses.Shocked;
import Lea.cards.words.Bye;
import Lea.cards.words.Wait;
import Lea.enums.customEnums;
import Lea.patches.AbstractCardEnum;
import Lea.relics.*;
import basemod.BaseMod;
import basemod.abstracts.CustomCard;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
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

    public static CardGroup combatArtStarterPool;
    public static CardGroup combatArtT1Pool;
    public static CardGroup combatArtT2Pool;
    public static CardGroup combatArtT3Pool;

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
        cards.add(new BoltDrink());
        cards.add(new Accumulation());
        cards.add(new StaticDash());
        cards.add(new BulletRain());
        cards.add(new VPRSpam());
        cards.add(new BlockCard());
        cards.add(new LimitlessSpam());
        cards.add(new Bullet());
        cards.add(new Flashback());
        cards.add(new Bye());
        cards.add(new EnergyConversion());
        cards.add(new AmberBreaker());
        cards.add(new SpinDance());
        cards.add(new HedgehagFarming());
        cards.add(new CheckWeaknesses());
        cards.add(new RockSmash());
        cards.add(new OverloadCooling());
        cards.add(new Durian());
        cards.add(new PreparingAssault());
        cards.add(new JumpOnWater());
        cards.add(new OneMoreTime());
        cards.add(new TargetAbsorb());
        cards.add(new Hackgey());
        cards.add(new IceTouch());
        cards.add(new VolcanoCrash());
        cards.add(new Patience());
        cards.add(new SapStrength());
        cards.add(new Sonar());
        cards.add(new WaveBounce());
        cards.add(new MemoryLost());
        cards.add(new Shocked());
        cards.add(new AFK());
        cards.add(new Ctron());
        cards.add(new Emilienator());
        cards.add(new CircuitPoint());
        cards.add(new FrozenStance());
        //Place receiveEditCards from caracters here with the cards variable on it

        combatArtStarterPool = new CardGroup(CardGroup.CardGroupType.CARD_POOL);
        combatArtT1Pool = new CardGroup(CardGroup.CardGroupType.CARD_POOL);
        combatArtT2Pool = new CardGroup(CardGroup.CardGroupType.CARD_POOL);
        combatArtT3Pool = new CardGroup(CardGroup.CardGroupType.CARD_POOL);

        for (AbstractCard c : cards) {
            if (c.rarity == customEnums.COMBAT_ART_STARTER) {
                combatArtStarterPool.addToBottom(c.makeCopy());
            }else if(c.rarity == customEnums.COMBAT_ART_T1){
                combatArtT1Pool.addToBottom(c.makeCopy());
            }else if(c.rarity == customEnums.COMBAT_ART_T2){
                combatArtT2Pool.addToBottom(c.makeCopy());
            }else if(c.rarity == customEnums.COMBAT_ART_T3){
                combatArtT3Pool.addToBottom(c.makeCopy());
            }
        }
        for(CustomCard card : cards) {

            LeaCrosscode.logger.info("Ajout de "+card.cardID + "---------------------------------------------------------------------------------------------------------------------------------\n\n");
            BaseMod.addCard(card);
            UnlockTracker.unlockCard(card.cardID);
        }


    }

    @Override
    public void receiveEditCharacters() {
        //J'le fais dans Lea.LeaCrosscode à voir pour restructurer comme le reste quand y'aura plusieurs persos.
    }

    @Override
    public void receiveEditKeywords() {
        BaseMod.addKeyword(new String[] {"protection", "protections", "prots"}, "Reduce by 2 + this amount damage taken this turn.");
        BaseMod.addKeyword(new String[] {"chill", "chills","chilled", "Chill", "Chills","Chilled"}, "DESCRIPTION A AJOUTER");
        BaseMod.addKeyword(new String[] {"burn", "burns", "burned", "Burn", "Burns", "Burned"}, "DESCRIPTION A AJOUTER");
        BaseMod.addKeyword(new String[] {"jolt", "jolts", "jolted", "Jolt", "Jolts", "Jolted"}, "DESCRIPTION A AJOUTER");
        BaseMod.addKeyword(new String[] {"mark", "marks", "marked", "Mark", "Marks", "Marked"}, "DESCRIPTION A AJOUTER");
        BaseMod.addKeyword(new String[] {"Target", "target"}, "Increase damage taken by BULLET by 2. Can only be applied to 1 ennemy.");
        BaseMod.addKeyword(new String[] {"Homing", "homing", "homed"}, "Shot a bullet to a random ennemy if no ennemy is marked.");

        BaseMod.addKeyword(new String[] {"Limitless Spam"}, "Each time you hit with a bullet, gain 1 temp Strength.");

    }

    @Override
    public void receiveEditRelics() {
        BaseMod.addRelicToCustomPool(new SpheromancerHeart(), AbstractCardEnum.LEA_COBALT);
        BaseMod.addRelicToCustomPool(new PreciseFocus(), AbstractCardEnum.LEA_COBALT);
        BaseMod.addRelicToCustomPool(new MartialCounterAttack(), AbstractCardEnum.LEA_COBALT);
        BaseMod.addRelicToCustomPool(new ConductiveRock(), AbstractCardEnum.LEA_COBALT);
        BaseMod.addRelicToCustomPool(new TurtleShell(), AbstractCardEnum.LEA_COBALT);
        BaseMod.addRelicToCustomPool(new EasyMode(), AbstractCardEnum.LEA_COBALT);
        BaseMod.addRelicToCustomPool(new BulletStock(), AbstractCardEnum.LEA_COBALT);
        BaseMod.addRelicToCustomPool(new FieryTitansGauntlet(), AbstractCardEnum.LEA_COBALT);
        BaseMod.addRelicToCustomPool(new MassCarnage(), AbstractCardEnum.LEA_COBALT);

    }

    @Override
    public void receiveEditStrings() {
        // RelicStrings
        String relicStrings = Gdx.files.internal("localization/Lea-RelicStrings.json").readString(String.valueOf(StandardCharsets.UTF_8));
        BaseMod.loadCustomStrings(RelicStrings.class, relicStrings);
        // CardStrings
        String cardStrings = Gdx.files.internal("localization/Lea-CardStrings.json").readString(String.valueOf(StandardCharsets.UTF_8));
        BaseMod.loadCustomStrings(CardStrings.class, cardStrings);

        // PotionStrings
        String potionStrings = Gdx.files.internal("localization/Lea-PotionStrings.json").readString(String.valueOf(StandardCharsets.UTF_8));
        BaseMod.loadCustomStrings(RelicStrings.class, potionStrings);
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
        //Crash toujours
        ArrayList<AbstractCard> rewardCards = AbstractDungeon.getRewardCards();
        if (!rewardCards.isEmpty()) {
            AbstractCard randomCard = rewardCards.get(AbstractDungeon.cardRng.random(rewardCards.size() - 1));
        }else{
            // Log un message d'erreur pour debug
            System.out.println("Erreur: Aucun Reward Card disponible !");
        }
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

