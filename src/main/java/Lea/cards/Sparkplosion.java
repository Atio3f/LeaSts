package Lea.cards;

import Lea.Abstracts.CrosscodeCard;
import Lea.characters.Lea;
import Lea.enums.customEnums;
import Lea.patches.AbstractCardEnum;
import Lea.powers.JoltPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.GainStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// Coût 1 : Apply 4(6) Jolt and 2 Jolt to a random ennemi Common
public class Sparkplosion extends CrosscodeCard {
    public static final String ID = "leacrosscode:Sparkplosion";
    private static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

    // Get object containing the strings that are displayed in the game.
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/Lea/cards/Dash.png"; //IMAGE A CHANGER
    private static final int COST = 1;

    private static final int JOLT_TARGET_AMT = 4;
    private static final int JOLT_RANDOM_AMT = 2;
    private static final int UPGRADE_PLUS_JOLT = 2;

    private static final int SP_COST = 0;
    private static final int SP_GAIN = 0;

    public static final Logger logger = LogManager.getLogger(Lea.class.getName());
    public Sparkplosion() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
            AbstractCard.CardType.SKILL, AbstractCardEnum.LEA_COBALT,
            CardRarity.COMMON, CardTarget.ENEMY, SP_COST, SP_GAIN);//p-ê commune
        logger.info("Sparkplosion" + DESCRIPTION + "---------------------------------------------------------------------------------------------------------------------------------\n\n");
        this.magicNumber = this.baseMagicNumber = JOLT_TARGET_AMT;


        tags.add(customEnums.SHOCK);

    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {

        addToBot(new ApplyPowerAction(m, p, new JoltPower(m, p, this.magicNumber), this.magicNumber, true, AbstractGameAction.AttackEffect.BLUNT_LIGHT));
        AbstractMonster mRandom = AbstractDungeon.getMonsters().getRandomMonster(m, true);
        if(mRandom != null){
            addToBot(new ApplyPowerAction(m, p, new JoltPower(m, p, JOLT_RANDOM_AMT), JOLT_RANDOM_AMT, true, AbstractGameAction.AttackEffect.NONE));
        }

        super.use(p, m);
    }

    @Override
    public AbstractCard makeCopy() {
        logger.info("SparkplosionCOPY---------------------------------------------------------------------------------------------------------------------------------\n\n");
        return new Sparkplosion();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(UPGRADE_PLUS_JOLT);
        }
    }
}
