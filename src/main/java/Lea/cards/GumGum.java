package Lea.cards;

import Lea.Abstracts.CrosscodeCard;
import Lea.actions.RegainElementalAction;
import Lea.characters.Lea;
import Lea.enums.TypeDegats;
import Lea.enums.customEnums;
import Lea.patches.AbstractCardEnum;
import Lea.powers.ProtectionPower;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
//PAS FINI D'IMPLEMENTER
public class GumGum extends CrosscodeCard {
    public static final String ID = "leacrosscode:GumGum";
    private static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

    // Get object containing the strings that are displayed in the game.
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/Lea/cards/Hit&Block.png";    //IMAGE A CHANGER
    private static final int COST = 1;
    private static final int BLOCK_AMT = 8;
    private static final int UPGRADE_BLOCK_AMT = 3;
    private static final int PROT_REDUCTION = 1;
    private static final int SP_COST = 0;
    private static final int SP_GAIN = 0;
    private static final int WAVE_GAIN = 3;
    private static final int SHOCK_LOSE = -2;
    private static final Integer[] elementsGain = {0, 0, SHOCK_LOSE, WAVE_GAIN};

    private static final TypeDegats TYPE_DEGATS = TypeDegats.NEUTRAL;
    public static final Logger logger = LogManager.getLogger(Lea.class.getName());
    public GumGum() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
            AbstractCard.CardType.SKILL, AbstractCardEnum.LEA_COBALT,
            CardRarity.COMMON, CardTarget.SELF, SP_COST, SP_GAIN);
        logger.info("GumGum" + DESCRIPTION + "---------------------------------------------------------------------------------------------------------------------------------\n\n");

        this.block = this.baseBlock = BLOCK_AMT;
        tags.add(customEnums.WAVE);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p, block));
        addToBot(new ReducePowerAction(p, p, "leacrosscode:ProtectionPower", PROT_REDUCTION));
        //Maybe add something to allow Protection to be negative
        addToBot(new RegainElementalAction(p, elementsGain));
        super.use(p, m);
    }

    @Override
    public AbstractCard makeCopy() {
        logger.info("GumGumCOPY---------------------------------------------------------------------------------------------------------------------------------\n\n");
        return new GumGum();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBlock(UPGRADE_BLOCK_AMT);
            elementsGain[3] += 1;
        }
    }
}
