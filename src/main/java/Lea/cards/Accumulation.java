package Lea.cards;

import Lea.Abstracts.CrosscodeCard;
import Lea.Abstracts.CrosscodeCharacter;
import Lea.characters.Lea;
import Lea.patches.AbstractCardEnum;
import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsInHandAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.common.DiscardSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

//Draw 2(3) cards. Discard up to 3(4) cards. Gain 1 SP for each card discard.
public class Accumulation extends CrosscodeCard {
    public static final String ID = "leacrosscode:Accumulation";
    private static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

    // Get object containing the strings that are displayed in the game.
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/Lea/cards/Dash.png";     //IMAGE A CHANGER
    private static final int COST = 1;
    private static final int CARD_DRAW = 2;
    private static final int UPGRADE_PLUS_CARD = 1;
    private static final int SP_COST = 0;
    private static final int SP_GAIN = 0;


    public static final Logger logger = LogManager.getLogger(Lea.class.getName());

    public Accumulation() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
            AbstractCard.CardType.SKILL, AbstractCardEnum.LEA_COBALT,
            CardRarity.UNCOMMON, CardTarget.SELF, SP_COST, SP_GAIN);
        logger.info("Accumulation" + DESCRIPTION + "---------------------------------------------------------------------------------------------------------------------------------\n\n");
        this.magicNumber = this.baseMagicNumber = CARD_DRAW;
        this.initializeDescription();


    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new DrawCardAction(p, this.magicNumber));

        /*int count = p.hand.size();  //On enregistre combien de cartes le joueur a avant de défausser des cartes
        //AbstractDungeon.actionManager.addToBottom(new DiscardAction(p, p, this.magicNumber + 1, false));
        AbstractDungeon.actionManager.addToBottom(new SelectCardsInHandAction(
            this.magicNumber + 1,  // Nombre max de cartes que le joueur peut choisir
            "Choose up to " + (this.magicNumber + 1) + " cards to discard",
            true,  // anyNumber : le joueur peut sélectionner jusqu'à la limite donnée
            true,  // canPickZero : il peut choisir 0 carte
            c -> true,  // Aucun filtre : toutes les cartes peuvent être sélectionnées
            selectedCards -> {
                for (AbstractCard c : selectedCards) {
                    AbstractDungeon.actionManager.addToBottom(new DiscardSpecificCardAction(c, AbstractDungeon.player.hand));
                }
            }
        ));
        if(p.getClass().getSuperclass().getSimpleName().equals("CrosscodeCharacter")){
            p.gainEnergy(p.hand.size() - count);    //On gagne 1 SP par carte défaussée
            logger.info("SP joueur avant : "+ (((CrosscodeCharacter) p).getSP_fight()));
            logger.info("GAIN SP : "+(p.hand.size() - count)+ "----------------------------------------------------------");
            logger.info("SP joueur après : "+ (((CrosscodeCharacter) p).getSP_fight()));
        }else{
            LogManager.getLogger(Lea.class.getName()).info("PROB DE CLASSE PERSO Pour Accumulation------------------------------------------------------");

        }*/
        int count = p.hand.size();  // Nombre de cartes en main avant la défausse
        logger.info("SP joueur avant : " + (((CrosscodeCharacter) p).getSP_fight()));
        AbstractDungeon.actionManager.addToBottom(new SelectCardsInHandAction(
            this.magicNumber + 1,  // Nombre max de cartes que le joueur peut choisir
            "Choose up to " + (this.magicNumber + 1) + " cards to discard",
            true,  // anyNumber : le joueur peut sélectionner jusqu'à la limite donnée
            true,  // canPickZero : il peut choisir 0 carte
            c -> true,  // Toutes les cartes sont éligibles
            selectedCards -> {
                int discardedCount = selectedCards.size(); // Nombre de cartes réellement défaussées
                for (AbstractCard c : selectedCards) {
                    AbstractDungeon.actionManager.addToBottom(new DiscardSpecificCardAction(c, AbstractDungeon.player.hand));
                }

                // Ajouter une action pour donner le SP après la défausse
                AbstractDungeon.actionManager.addToBottom(new AbstractGameAction() {
                    @Override
                    public void update() {
                        if (p instanceof CrosscodeCharacter) { // Vérifie que c'est bien ton personnage
                            ((CrosscodeCharacter) p).gainSP(discardedCount);

                            logger.info("GAIN SP : " + discardedCount + "----------------------------------------------------------");
                            logger.info("SP joueur après : " + (((CrosscodeCharacter) p).getSP_fight()));
                        } else {
                            LogManager.getLogger(Lea.class.getName()).info("PROB DE CLASSE PERSO Pour Accumulation------------------------------------------------------");
                        }
                        this.isDone = true; // Marquer l'action comme terminée
                    }
                });
            }
        ));
        super.use(p, m);
    }

    @Override
    public AbstractCard makeCopy() {
        logger.info("AccumulationCOPY---------------------------------------------------------------------------------------------------------------------------------\n\n");
        return new Accumulation();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(UPGRADE_PLUS_CARD);
            this.initializeDescription();
        }
    }

    @Override
    public void initializeDescription() {
        this.rawDescription = CardCrawlGame.languagePack.getCardStrings(ID).DESCRIPTION;
        this.rawDescription = this.rawDescription.replace("!D!", Integer.toString(this.magicNumber + 1));
        super.initializeDescription();
    }
}
