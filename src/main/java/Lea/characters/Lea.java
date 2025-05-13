package Lea.characters;

import java.util.ArrayList;


import Lea.Abstracts.CrosscodeCharacter;
import Lea.cards.Hit;
import basemod.BaseMod;
import basemod.abstracts.CustomEnergyOrb;
import basemod.abstracts.CustomPlayer;
import basemod.animations.AbstractAnimation;
import basemod.animations.G3DJAnimation;
import basemod.animations.SpriterAnimation;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.MathUtils;
import com.esotericsoftware.spine.AnimationState;
import com.evacipated.cardcrawl.modthespire.lib.SpireEnum;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.ExhaustAllEtherealAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.blue.Defend_Blue;
import com.megacrit.cardcrawl.cards.green.Neutralize;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.characters.TheSilent;
import com.megacrit.cardcrawl.core.EnergyManager;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.screens.CharSelectInfo;
import com.megacrit.cardcrawl.unlock.UnlockTracker;

import Lea.patches.AbstractCardEnum;
import Lea.patches.LeaEnum;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Lea extends CrosscodeCharacter {

    public static final int ENERGY_PER_TURN = 3; // how much energy you get every turn
    public static final String MY_CHARACTER_SHOULDER_2 = "img/Lea/characters/Lea/shoulder2.png"; // campfire pose
    public static final String MY_CHARACTER_SHOULDER_1 = "img/Lea/characters/Lea/shoulder1.png"; // another campfire pose
    public static final String MY_CHARACTER_CORPSE = "img/Lea/characters/Lea/corpse.png"; // dead corpse
    public static final String MY_CHARACTER_SKELETON_ATLAS = "img/Lea/characters/Lea/idle/skeleton.atlas"; // spine animation atlas
    public static final String MY_CHARACTER_SKELETON_JSON = "img/Lea/characters/Lea/idle/skeleton.json"; // spine animation json

    public static final String[] orbTextures = {
        "img/Lea/orbs/layer1.png",
        "img/Lea/orbs/layer2.png",
        "img/Lea/orbs/layer3.png",
        "img/Lea/orbs/layer4.png",
        "img/Lea/orbs/layer5.png",
        "img/Lea/orbs/layer6.png",
        "img/Lea/orbs/layer1d.png",
        "img/Lea/orbs/layer2d.png",
        "img/Lea/orbs/layer3d.png",
        "img/Lea/orbs/layer4d.png",
        "img/Lea/orbs/layer5d.png",
    };
    public static final Logger logger = LogManager.getLogger(Lea.class.getName());
    public static final Color COBALT = CardHelper.getColor(45.0f, 27.0f, 175.0f);   //A changer si on change celui dans LeaMod


    public Lea (String name) {
        //orbTextures, "img/Lea/orbs/vfx.png"
        super(name, LeaEnum.LEA, null, null, (float[])null, (new SpriterAnimation("img/Lea/char/animation.scml")));
        logger.info("ENFIN---------------------------------------------------------------------------------------------------------------------------------\n\n");
        this.dialogX = (this.drawX + 0.0F * Settings.scale); // set location for text bubbles
        this.dialogY = (this.drawY + 220.0F * Settings.scale); // you can just copy these values

        initializeClass(null, MY_CHARACTER_SHOULDER_2, // required call to load textures and setup energy/loadout
            MY_CHARACTER_SHOULDER_1,
            MY_CHARACTER_CORPSE,
            getLoadout(), 20.0F, -10.0F, 220.0F, 290.0F, new EnergyManager(ENERGY_PER_TURN));

        loadAnimation(MY_CHARACTER_SKELETON_ATLAS, MY_CHARACTER_SKELETON_JSON, 1.0F); // if you're using modified versions of base game animations or made animations in spine make sure to include this bit and the following lines
        logger.info("ENFIN2---------------------------------------------------------------------------------------------------------------------------------\n\n");
        /* AnimationState.TrackEntry e = this.state.setAnimation(0, "animation", true);
        e.setTime(e.getEndTime() * MathUtils.random()); */
    }

    public ArrayList<String> getStartingDeck() { // starting deck 'nuff said
        logger.info("ENFIN8---------------------------------------------------------------------------------------------------------------------------------\n\n");
        ArrayList<String> cartes = new ArrayList<>();
        cartes.add("leacrosscode:Hit");
        cartes.add("leacrosscode:Hit");
        cartes.add("leacrosscode:Hit");
        cartes.add("leacrosscode:Hit");
        cartes.add("leacrosscode:Dash");
        cartes.add("leacrosscode:Dash");
        cartes.add("leacrosscode:Block");
        cartes.add("leacrosscode:Block");
        cartes.add("leacrosscode:Block");
        cartes.add("leacrosscode:OverloadCooling");
        cartes.add("leacrosscode:MemoryLost");

        cartes.add("leacrosscode:SphereSaw");
        cartes.add("leacrosscode:VPRSpam");
        LeaMod.combatArtStarterPool.getRandomCard
        System.out.println(cartes + "ENFIN9-----------------------------------------------------------------------------------------------------------------------------\n\n");
        return cartes;
    }

    public ArrayList<String> getStartingRelics() { // starting relics - also simple
        logger.info("ENFIN3---------------------------------------------------------------------------------------------------------------------------------\n\n");
        ArrayList<String> relics = new ArrayList<>();
        //relics.add("Akabeko");
        relics.add("SpheromancerHeart");
        //UnlockTracker.markRelicAsSeen("Akabeko");
        UnlockTracker.markRelicAsSeen("SpheromancerHeart");
        logger.info("ENFIN4---------------------------------------------------------------------------------------------------------------------------------\n\n");
        return relics;
    }

    public static final int STARTING_HP = 80;
    public static final int MAX_HP = 80;
    public static final int ORB_SLOTS = 0;
    public static final int STARTING_GOLD = 99;
    public static final int HAND_SIZE = 5;

    @Override
    public CharSelectInfo getLoadout() { // the rest of the character loadout so includes your character select screen info plus hp and starting gold
        logger.info("ENFIN5---------------------------------------------------------------------------------------------------------------------------------\n\n");
        return new CharSelectInfo("Lea", "A quiet blue-haired spheromancer.",
            STARTING_HP, MAX_HP, ORB_SLOTS, STARTING_GOLD, HAND_SIZE,
            this, getStartingRelics(), getStartingDeck(), false);
    }

    @Override
    public String getTitle(PlayerClass playerClass) {
        logger.info("getTitle ---------------------------------------------------------------------------------------------------------------------------------\n\n");
        return "Lea";
    }

    @Override
    public AbstractCard.CardColor getCardColor() {
        //logger.info("APOTRE---------------------------------------------------------------------------------------------------------------------------------\n\n");
        return AbstractCardEnum.LEA_COBALT;
    }

    @Override
    public Color getCardRenderColor() {
        logger.info("getCardRenderColor ---------------------------------------------------------------------------------------------------------------------------------\n\n");
        return COBALT;
    }

    @Override
    public AbstractCard getStartCardForEvent() {
        logger.info("getStartCardForEvent ---------------------------------------------------------------------------------------------------------------------------------\n\n");

        return new Neutralize();
    }

    @Override
    public Color getCardTrailColor() {
        //logger.info("getCardTrailColor ---------------------------------------------------------------------------------------------------------------------------------\n\n");
        return COBALT;
    }

    @Override
    public int getAscensionMaxHPLoss() {
        logger.info("getAscensionMaxHPLoss ---------------------------------------------------------------------------------------------------------------------------------\n\n");
        return 18;
    }

    @Override
    public BitmapFont getEnergyNumFont() {
        //logger.info("getEnergyNumFont ---------------------------------------------------------------------------------------------------------------------------------\n\n");
        return FontHelper.energyNumFontBlue;
    }

    @Override
    public void doCharSelectScreenSelectEffect() {
        logger.info("doCharSelectScreenSelectEffect ---------------------------------------------------------------------------------------------------------------------------------\n\n");

    }

    @Override
    public String getCustomModeCharacterButtonSoundKey() {
        logger.info("getCustomModeCharacterButtonSoundKey ---------------------------------------------------------------------------------------------------------------------------------\n\n");
        return "";
    }

    @Override
    public String getLocalizedCharacterName() {
        logger.info("getLocalizedCharacterName ---------------------------------------------------------------------------------------------------------------------------------\n\n");
        return "Lea";
    }

    @Override
    public AbstractPlayer newInstance() {
        logger.info("PAS ENFIN ---------------------------------------------------------------------------------------------------------------------------------\n\n");
        return new Lea(this.name);
    }

    @Override
    public String getSpireHeartText() {
        return "WOUHOU";
    }

    @Override
    public Color getSlashAttackColor() {
        return CardHelper.getColor(45.0f, 27.0f, 175.0f);   //Changer si jamais on change dans LeaMod la couleur du Cobalt
    }

    @Override
    public AbstractGameAction.AttackEffect[] getSpireHeartSlashEffect() {
        return new AbstractGameAction.AttackEffect[0];
    }

    @Override
    public String getVampireText() {
        return "Are you a cheater?";
    }
}
