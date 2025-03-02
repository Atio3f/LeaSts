package Lea.cards.consumables;

import Lea.Abstracts.CrosscodeCard;
import Lea.characters.Lea;
import Lea.enums.TypeDegats;
import Lea.enums.customEnums;
import Lea.patches.AbstractCardEnum;
import Lea.powers.JoltPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BoltDrink extends CrosscodeCard {
    public static final String ID = "leacrosscode:BoltDrink";
    private static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

    // Get object containing the strings that are displayed in the game.
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/Lea/cards/Dash.png"; //IMAGE A CHANGER
    private static final int COST = 0;
    private static final int SP_COST = 0;
    private static final int SP_GAIN = 0;

    private static final int STATS = 1; //Affecte le nombre de cartes piochées et les PV Max gagnés
    private static final int STATS_UPGRADE = 1; //Affecte le nombre de cartes piochées et les PV Max gagnés après l'amélioration


    public static final Logger logger = LogManager.getLogger(Lea.class.getName());
    public BoltDrink() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
            AbstractCard.CardType.SKILL, AbstractCardEnum.LEA_COBALT,
            CardRarity.RARE, AbstractCardEnum.FRONT, SP_COST, SP_GAIN);
        logger.info("BoltDrink" + DESCRIPTION + "---------------------------------------------------------------------------------------------------------------------------------\n\n");
        this.magicNumber = this.baseMagicNumber = this.STATS;

        this.exhaust = true;
        this.isInnate = true;
        this.isEthereal = true;

    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.player.increaseMaxHp(magicNumber, true);
        AbstractDungeon.actionManager.addToBottom(new DrawCardAction(p, this.magicNumber));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p,
            p, new JoltPower(p, p, 1), 1, true, AbstractGameAction.AttackEffect.BLUNT_LIGHT));

        super.use(p, m);
    }

    @Override
    public AbstractCard makeCopy() {
        logger.info("BoltDrinkCOPY---------------------------------------------------------------------------------------------------------------------------------\n\n");
        return new BoltDrink();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(STATS_UPGRADE);
        }
    }
}
//(consommable, Légendaire)  Coût 0 énergie : Innate Ethereal +1(2)PV max and Draw a(2) card. Give 1 Jolt. Exhaust
