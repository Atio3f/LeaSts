package Lea.cards;

import Lea.LeaMod;
import Lea.Abstracts.CrosscodeCard;
import Lea.characters.Lea;
import Lea.patches.AbstractCardEnum;
import com.evacipated.cardcrawl.mod.stslib.cards.interfaces.OnObtainCard;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.random.Random;
import com.megacrit.cardcrawl.rewards.RewardItem;
import com.megacrit.cardcrawl.screens.CombatRewardScreen;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

public class CircuitPoint extends CrosscodeCard implements OnObtainCard{
    public static final String ID = "leacrosscode:CircuitPoint";
    private static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

    // Get object containing the strings that are displayed in the game.
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
    public static final String IMG_PATH = "img/Lea/cards/Hit&Block.png";    //IMAGE A CHANGER
    private static final int COST = 0;
    private static final int T2_CHANCE = 7;    //6 = 35% of chance to have a T2 combat art on choice so 65% to get a T1 combat art on choice
    private static final int UPGRADE_T2_CHANCE = 2; //2 => +10% of chance to have a T2 combat art on choice when upgraded
    private static int T3chance = 0; //0 = 0% of chance to have a T3 combat art on choice
    private static final int UPGRADE_T3_CHANCE = 2; //2 = 10% of chance to have a T3 combat art on choice when upgraded

    private static final int SP_COST = 0;
    private static final int SP_GAIN = 0;

    public static final Logger logger = LogManager.getLogger(Lea.class.getName());
    public CircuitPoint() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
            CardType.POWER, AbstractCardEnum.LEA_COBALT,
            CardRarity.RARE, CardTarget.NONE, SP_COST, SP_GAIN);
        logger.info("CircuitPoint" + DESCRIPTION + "---------------------------------------------------------------------------------------------------------------------------------\n\n");

        this.magicNumber = this.baseMagicNumber = T2_CHANCE;
        tags.add(CardTags.HEALING); //Permet d'empêcher d'obtenir la carte via dead branch

    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        super.use(p, m);
    }

    @Override
    public void onObtainCard() {
        ArrayList<AbstractCard> rewardCards = new ArrayList<>();
        AbstractCard card;
        Integer random;    //Rng of the type of pool get
        Random rng; //Rng concerning the seed and the card get from pool
        for(int i=0; i<3; i++){
            random = AbstractDungeon.cardRandomRng.random(19);
            rng  = Settings.seed != null ? new Random(Settings.seed) : new Random();
            if(random < T3chance){
                card = LeaMod.combatArtT3Pool.getRandomCard(rng).makeCopy();
            } else if (random < (T3chance + magicNumber)) {
                card = LeaMod.combatArtT2Pool.getRandomCard(rng).makeCopy();
            }else{
                card = LeaMod.combatArtT1Pool.getRandomCard(rng).makeCopy();
            }
            rewardCards.add(card);
        }

        if (!rewardCards.isEmpty()) {
            RewardItem reward = new RewardItem();
            //Clear the generate by default cards
            reward.cards.clear();
            //Add our cards
            reward.cards.addAll(rewardCards);
            AbstractDungeon.combatRewardScreen.rewards.add(reward);
        }
        //Delete the card from your deck once you got the screen reward
        for (AbstractCard carteDeck : AbstractDungeon.player.masterDeck.group) {
            if (carteDeck.uuid == this.uuid) {
                AbstractDungeon.player.masterDeck.removeCard(carteDeck);
                break;
            }
        }
    }

    @Override
    public AbstractCard makeCopy() {
        logger.info("CircuitPointCOPY---------------------------------------------------------------------------------------------------------------------------------\n\n");
        return new CircuitPoint();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(UPGRADE_T2_CHANCE);
            T3chance += UPGRADE_T3_CHANCE;
            rawDescription = UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }
}
