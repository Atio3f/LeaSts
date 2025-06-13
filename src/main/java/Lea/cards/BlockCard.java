package Lea.cards;

import Lea.Abstracts.CrosscodeCard;
import Lea.characters.Lea;
import Lea.enums.TypeDegats;
import Lea.enums.customEnums;
import Lea.patches.AbstractCardEnum;
import Lea.powers.ProtectionPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BlockCard extends CrosscodeCard {
    public static final String ID = "leacrosscode:Block";
    private static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

    // Get object containing the strings that are displayed in the game.
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/Lea/cards/Hit&Block.png";    //IMAGE A CHANGER
    private static final int COST = 1;
    private static final int BLOCK_VALUE = 4;   //Up de 1 pour compenser le nerf des prots
    private static final int UPGRADE_PLUS_BLOCK = 2;
    private static final int PROT_VALUE = 2;
    private static final int UPGRADE_PLUS_PROT = 1;
    private static final int SP_COST = 0;
    private static final int SP_GAIN = 0;

    private static final TypeDegats TYPE_DEGATS = TypeDegats.NEUTRAL;
    public static final Logger logger = LogManager.getLogger(Lea.class.getName());
    public BlockCard() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
            AbstractCard.CardType.SKILL, AbstractCardEnum.LEA_COBALT,
            CardRarity.BASIC, CardTarget.SELF, SP_COST, SP_GAIN);
        logger.info("Block" + DESCRIPTION + "---------------------------------------------------------------------------------------------------------------------------------\n\n");

        this.block=this.baseBlock = BLOCK_VALUE;
        this.magicNumber = this.baseMagicNumber = PROT_VALUE;
        tags.add(customEnums.NEUTRAL);
        tags.add(customEnums.SHIELD);
        tags.add(CardTags.STARTER_DEFEND);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p,
            p, this.block));
        addToBot(new ApplyPowerAction(p,
            p, new ProtectionPower(p, this.magicNumber), this.magicNumber, true, AbstractGameAction.AttackEffect.SHIELD));
        super.use(p, m);
    }

    @Override
    public AbstractCard makeCopy() {
        logger.info("BlockCardCOPY---------------------------------------------------------------------------------------------------------------------------------\n\n");
        return new BlockCard();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBlock(UPGRADE_PLUS_BLOCK);
            this.upgradeMagicNumber(UPGRADE_PLUS_PROT);
        }
    }
}
