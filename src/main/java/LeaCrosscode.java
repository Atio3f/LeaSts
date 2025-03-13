import Lea.Abstracts.CrosscodeCard;
import Lea.characters.Lea;
import Lea.patches.AbstractCardEnum;
import Lea.patches.LeaEnum;
import Lea.patches.SP_GAIN;
import Lea.relics.*;
import basemod.AutoAdd;
import basemod.interfaces.*;
import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

import basemod.BaseMod;
import org.apache.logging.log4j.LogManager;

import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

@SpireInitializer
public class LeaCrosscode implements
    EditCardsSubscriber, PostBattleSubscriber, PostDungeonInitializeSubscriber, EditRelicsSubscriber, EditCharactersSubscriber, EditStringsSubscriber, EditKeywordsSubscriber, PostPlayerUpdateSubscriber {

    public static final Logger logger = LogManager.getLogger(LeaCrosscode.class.getName());

    private int count, totalCount;
    private List<CharacterMod> persos;  //Contient la liste de tous les persos au cas où l'on rajoute les autres classes de Crosscode

    private void resetCounts() {
        totalCount = count = 0;
    }

    public LeaCrosscode() {
        BaseMod.subscribe(this);
        //BaseMod.subscribeToEditCharacters(this);
        persos = new ArrayList<>();
        persos.add(new LeaMod());
        resetCounts();
    }

    public static void initialize() {
        @SuppressWarnings("unused")
        LeaCrosscode lea = new LeaCrosscode();
    }

    @Override
    public void receiveEditStrings() {
        for(EditStringsSubscriber perso : persos) {
            perso.receiveEditStrings();
        }
    }
    @Override
    public void receiveEditCards() {
        logger.info("NOUVELLES CARTES----------------------------------------------------------------------------------------------------------------------------\n\n");
        BaseMod.addDynamicVariable(new SP_GAIN());
        for(EditCardsSubscriber perso : persos) {
            perso.receiveEditCards();
        }
        logger.info("AIE----------------------------------------------------------------------------------------------------------------------------\n\n");
        new AutoAdd("leacrosscode") //Loads files from this mod
            .packageFilter(CrosscodeCard.class) //In the same package as this class
            .setDefaultSeen(true) //And marks them as seen in the compendium
            .cards(); //Adds the cards
        logger.info("FIN NOUVELLES CARTES-------------------------------------------------------------------------------------------------------\n\n");
    }
    @Override
    public void receiveEditCharacters() {
        logger.info("begin editing characters");

        logger.info("add " + LeaEnum.LEA.toString());
        String nom = CardCrawlGame.playerName!= null ? CardCrawlGame.playerName  : "Léa";
       BaseMod.addCharacter(new Lea(nom),
            "img/Lea/charSelect/LeaButton.png",
            "img/Lea/charSelect/LeaPortrait.jpg",
            LeaEnum.LEA);

        logger.info("done editing characters");
    }
/*
    @Override
    public void receivePostExhaust(AbstractCard c) {
        count++;
        totalCount++;
    }*/

    @Override
    public void receivePostBattle(AbstractRoom r) {
//        System.out.println(count + " cards were exhausted this battle, " +
//            totalCount + " cards have been exhausted so far this act.");
//        count = 0;
        System.out.println("RECOMPENSES" + AbstractDungeon.getRewardCards());
        for(PostBattleSubscriber mod : persos) {
            mod.receivePostBattle(r);
        }
    }

    @Override
    public void receivePostDungeonInitialize() {
        //resetCounts();
        for(PostDungeonInitializeSubscriber mod : persos) {
            mod.receivePostDungeonInitialize();
        }
    }

    @Override
    public void receiveEditRelics() {
        logger.info("NOUVELLES RELIQUES----------------------------------------------------------------------------------------------------------------------------\n\n");

        BaseMod.addRelicToCustomPool(new SpheromancerHeart(), AbstractCardEnum.LEA_COBALT);
        BaseMod.addRelicToCustomPool(new PreciseFocus(), AbstractCardEnum.LEA_COBALT);
        BaseMod.addRelicToCustomPool(new MartialCounterAttack(), AbstractCardEnum.LEA_COBALT);
        BaseMod.addRelicToCustomPool(new ConductiveRock(), AbstractCardEnum.LEA_COBALT);
        BaseMod.addRelicToCustomPool(new TurtleShell(), AbstractCardEnum.LEA_COBALT);

        /*for(EditCardsSubscriber perso : persos) {
            perso.receiveEditRelics();
        }*/

        logger.info("FIN NOUVELLES RELIQUES-------------------------------------------------------------------------------------------------------\n\n");
    }


    @Override
    public void receiveEditKeywords() {
        for(EditKeywordsSubscriber mod : persos) {
            mod.receiveEditKeywords();
        }
    }

    @Override
    public void receivePostPlayerUpdate() {
        if(CardCrawlGame.isInARun() && AbstractDungeon.currMapNode != null && AbstractDungeon.getCurrRoom() != null && AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT){

        }
    }
}
