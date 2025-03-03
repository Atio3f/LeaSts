package Lea.actions;

import Lea.characters.Lea;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.ArrayList;
import java.util.Iterator;

//New action which allows me to change for the rest of combat the cost of a card taken from discard
public class CrosscodeDiscardPileToHandAction extends AbstractGameAction {
    public static final String[] TEXT;
    private AbstractPlayer player;
    private int numberOfCards;
    private boolean optional;
    private int newCost;
    private boolean setCost;
    //Attributs rajoutés
    private int costChange;

    private boolean tempCostChange;

    public CrosscodeDiscardPileToHandAction(int numberOfCards, boolean optional) {
        this.newCost = 0;
        this.costChange = 0;
        this.actionType = ActionType.CARD_MANIPULATION;
        this.duration = this.startDuration = Settings.ACTION_DUR_FAST;
        this.player = AbstractDungeon.player;
        this.numberOfCards = numberOfCards;
        this.optional = optional;
        this.setCost = false;
        this.tempCostChange = true;
    }

    public CrosscodeDiscardPileToHandAction(int numberOfCards) {
        this(numberOfCards, false);
    }

    /**
     *
     * @param numberOfCards number of cards who can be taken from discard
     * @param costChange cost change by     If value == 0, this param will not be used.
     * @param newCost new cost. If value == -1, this param will not be used.
     * @param tempCostChange    Define if the new cost change is temporary or not
     */
    public CrosscodeDiscardPileToHandAction(int numberOfCards, int costChange, int newCost, boolean tempCostChange) {
        this.newCost = 0;
        this.actionType = ActionType.CARD_MANIPULATION;
        this.duration = this.startDuration = Settings.ACTION_DUR_FAST;
        this.player = AbstractDungeon.player;
        this.numberOfCards = numberOfCards;
        this.optional = false;
        this.setCost = true;
        if(newCost == -1){
            this.costChange = costChange;
        }else{
            this.newCost = newCost;
        }
        this.tempCostChange = tempCostChange;
    }

    //Constructeur sans la partie coût total changé
    public CrosscodeDiscardPileToHandAction(int numberOfCards, int costChange, boolean tempCostChange) {
        this.newCost = 0;
        this.actionType = ActionType.CARD_MANIPULATION;
        this.duration = this.startDuration = Settings.ACTION_DUR_FAST;
        this.player = AbstractDungeon.player;
        this.numberOfCards = numberOfCards;
        this.optional = false;
        this.setCost = true;
        this.costChange = costChange;
        this.tempCostChange = tempCostChange;
    }

    public void update() {
        if (this.duration == this.startDuration) {
            if (!this.player.discardPile.isEmpty() && this.numberOfCards > 0) {

                if (this.player.discardPile.size() <= this.numberOfCards && !this.optional) {
                    ArrayList<AbstractCard> cardsToMove = new ArrayList();
                    Iterator var5 = this.player.discardPile.group.iterator();

                    AbstractCard c;
                    while(var5.hasNext()) {
                        c = (AbstractCard)var5.next();
                        cardsToMove.add(c);
                    }

                    var5 = cardsToMove.iterator();

                    while(var5.hasNext()) {
                        c = (AbstractCard)var5.next();
                        Lea.logger.info("BOUCLE :  "+ c.cost +" amount : "+amount  );

                        if (this.player.hand.size() < 10) {
                            this.player.hand.addToHand(c);
                            if (this.setCost) {
                                if(this.tempCostChange){
                                    c.setCostForTurn(this.newCost);
                                }else{
                                    Lea.logger.info("PROBLEME DE PRIX SUREMEBT " + c.cost + costChange + " costChange : " + costChange + " cost"+c.cost);
                                    c.modifyCostForCombat(c.cost + costChange);
                                    Lea.logger.info(c.cost);
                                }
                            }

                            this.player.discardPile.removeCard(c);
                        }

                        c.lighten(false);
                        c.applyPowers();
                    }

                    this.isDone = true;
                } else {
                    if (this.numberOfCards == 1) {
                        if (this.optional) {
                            AbstractDungeon.gridSelectScreen.open(this.player.discardPile, this.numberOfCards, true, TEXT[0]);
                        } else {
                            AbstractDungeon.gridSelectScreen.open(this.player.discardPile, this.numberOfCards, TEXT[0], false);
                        }
                    } else if (this.optional) {
                        AbstractDungeon.gridSelectScreen.open(this.player.discardPile, this.numberOfCards, true, TEXT[1] + this.numberOfCards + TEXT[2]);
                    } else {
                        AbstractDungeon.gridSelectScreen.open(this.player.discardPile, this.numberOfCards, TEXT[1] + this.numberOfCards + TEXT[2], false);
                    }

                    this.tickDuration();
                }
            } else {
                this.isDone = true;
            }
        } else {
            Iterator var1;
            AbstractCard c;
            if (!AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
                var1 = AbstractDungeon.gridSelectScreen.selectedCards.iterator();

                while(var1.hasNext()) {
                    c = (AbstractCard)var1.next();
                    if (this.player.hand.size() < 10) {
                        this.player.hand.addToHand(c);
                        if (this.setCost) {
                            if(this.tempCostChange){
                                Lea.logger.info("PROBLEME DE PRIX SUREMEBT 2"  + c.cost + costChange + " costChange : " + costChange + " cost"+c.cost);
                                c.setCostForTurn(this.newCost);
                            }else{
                                c.modifyCostForCombat(c.cost + costChange);
                            }
                        }

                        this.player.discardPile.removeCard(c);
                    }

                    c.lighten(false);
                    c.unhover();
                    c.applyPowers();
                }

                for(var1 = this.player.discardPile.group.iterator(); var1.hasNext(); c.target_y = 0.0F) {
                    c = (AbstractCard)var1.next();
                    c.unhover();
                    c.target_x = (float) CardGroup.DISCARD_PILE_X;
                }

                AbstractDungeon.gridSelectScreen.selectedCards.clear();
                AbstractDungeon.player.hand.refreshHandLayout();
            }

            this.tickDuration();
            if (this.isDone) {
                var1 = this.player.hand.group.iterator();

                while(var1.hasNext()) {
                    c = (AbstractCard)var1.next();
                    c.applyPowers();
                }
            }

        }
    }

    static {
        TEXT = CardCrawlGame.languagePack.getUIString("BetterToHandAction").TEXT;
    }
}
