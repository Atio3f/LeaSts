package Lea.cards;

import Lea.Abstracts.CrosscodeCard;
import Lea.cards.words.Wait;
import Lea.characters.Lea;
import Lea.enums.TypeDegats;
import Lea.enums.customEnums;
import Lea.patches.AbstractCardEnum;
import com.evacipated.cardcrawl.mod.stslib.variables.ExhaustiveVariable;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

//Converti 3 SP en 2 énergies Exhaustive 2
public class EnergyConversion extends CrosscodeCard {
    public static final String ID = "leacrosscode:EnergyConversion";
    private static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

    // Get object containing the strings that are displayed in the game.
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/Lea/cards/Wait.png";     //IMAGE A CHANGER
    private static final int COST = 0;
    private static final int SP_COST = 3;
    private static final int SP_GAIN = 0;

    private static final int ENERGY_GAIN = 2;
    private static final int EXHAUSTIVE = 2;
    private static final int UPGRADE_EXHAUSTIVE = 2;

    public static final Logger logger = LogManager.getLogger(Lea.class.getName());
    public EnergyConversion() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
            AbstractCard.CardType.SKILL, AbstractCardEnum.LEA_COBALT,
            CardRarity.COMMON, CardTarget.SELF, SP_COST, SP_GAIN);
        logger.info("EnergyConversion" + DESCRIPTION + "---------------------------------------------------------------------------------------------------------------------------------\n\n");

        tags.add(customEnums.NEUTRAL);
        ExhaustiveVariable.setBaseValue(this, EXHAUSTIVE);

    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new GainEnergyAction(this.ENERGY_GAIN));
        super.use(p, m);
    }

    @Override
    public AbstractCard makeCopy() {
        logger.info("EnergyConversionCOPY---------------------------------------------------------------------------------------------------------------------------------\n\n");
        return new EnergyConversion();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            ExhaustiveVariable.upgrade(this, UPGRADE_EXHAUSTIVE);
        }
    }
}

