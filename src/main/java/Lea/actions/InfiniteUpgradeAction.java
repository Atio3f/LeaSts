package Lea.actions;

import com.evacipated.cardcrawl.mod.stslib.cards.interfaces.BranchingUpgradesCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.ArrayList;
import java.util.List;

//Classe action créée à partir de SuperUpgradeAction dans Packmaster
public class InfiniteUpgradeAction extends AbstractGameAction {

    private List<AbstractCard> selectedCards = new ArrayList<>();

    public InfiniteUpgradeAction(AbstractCard carte, int amount) {
        this.amount = amount;
        this.selectedCards.add(carte);
    }

    public InfiniteUpgradeAction(List<AbstractCard> cartes, int amount) {
        this.amount = amount;
        for (AbstractCard carte : cartes) {
            this.selectedCards.add(carte);

            for (AbstractCard carteDeck : AbstractDungeon.player.masterDeck.group) {
                if (carteDeck.uuid == carte.uuid) {
                    for(int i=0;i<amount;i++){
                        InfiniteUpgradeAction.forceUpgrade(carteDeck);
                    }
                    break;
                }
            }
        }
    }

    @Override
    public void update() {
        //SpireAnniversary5Mod.logger.info("====== Updating SuperUpgrade action, with list " + selectedCards);

        for (AbstractCard c : selectedCards) {
            for (int i = 0 ; i < amount ; i++) {
                forceUpgrade(c);
            }
        }
        // In case this was played through an effect that didn't change the hand (e.g. a duplication effect),
        // make sure that card text gets updated to reflect the new values
        AbstractDungeon.player.hand.applyPowers();
        isDone = true;
    }

    public static void forceUpgrade(AbstractCard card) {
        if (card.canUpgrade()) {
            card.upgrade();
            return;
        }

        AbstractCard exampleCard = card.makeCopy();

        int oldMagic = card.baseMagicNumber;

        int baseCost = exampleCard.cost;
        exampleCard.upgrade();
        int upgCostDiff = exampleCard.cost - baseCost;
        int turnCostDiff = card.costForTurn - card.cost;
        int targetCost = card.cost;
        if (targetCost >= 0 && upgCostDiff != 0) {
            targetCost += upgCostDiff;

            if (targetCost < 0)
                targetCost = 0;
        }

        card.upgraded = false;
        if (card instanceof BranchingUpgradesCard) {
            BranchingUpgradesCard c = (BranchingUpgradesCard)card;
            if (c.isBranchUpgrade()) {
                c.doBranchUpgrade();
            } else {
                c.doNormalUpgrade();
            }
        } else {
            card.upgrade();
        }

        if (card.baseMagicNumber < 1) {
            card.baseMagicNumber = card.magicNumber = oldMagic;
        }

        if (card.cost > targetCost && card.cost >= 0) {
            card.cost = targetCost;
            card.costForTurn = targetCost + turnCostDiff;
            if (card.costForTurn < 0)
                card.costForTurn = 0;
            card.isCostModifiedForTurn = card.costForTurn != card.cost;
        }

        if (card.timesUpgraded > 1) {
            card.name = card.originalName + "+" + card.timesUpgraded;
        }
        if (card.timesUpgraded < 1) {
            card.name = card.originalName + "*" + (-card.timesUpgraded);
        }
    }
}
