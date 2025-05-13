package Lea.cards;

import Lea.Abstracts.CrosscodeCard;
import Lea.Abstracts.FrontTargeting;
import Lea.characters.Lea;
import Lea.enums.TypeDegats;
import Lea.enums.customEnums;
import Lea.patches.AbstractCardEnum;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RockSmash extends CrosscodeCard {
    public static final String ID = "leacrosscode:RockSmash";
    private static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

    // Get object containing the strings that are displayed in the game.
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/Lea/cards/Hit.png";  //IMAGE A CHANGER
    private static final int COST = 1;
    private static final int ATTACK_DMG = 5;
    private static final int UPGRADE_PLUS_DMG = 3;
    private static final int BLOCK_VALUE = 5;
    private static final int UPGRADE_PLUS_BLOCK = 2;
    private static final int SP_COST = 0;
    private static final int SP_GAIN = 1;

    private static final TypeDegats TYPE_DEGATS = TypeDegats.NEUTRAL;
    public static final Logger logger = LogManager.getLogger(Lea.class.getName());
    public RockSmash() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
            AbstractCard.CardType.ATTACK, AbstractCardEnum.LEA_COBALT,
            CardRarity.COMMON, AbstractCardEnum.FRONT, SP_COST, SP_GAIN);
        logger.info("RockSmash" + DESCRIPTION + "---------------------------------------------------------------------------------------------------------------------------------\n\n");
        this.damage=this.baseDamage = ATTACK_DMG;
        this.block=this.baseBlock = BLOCK_VALUE;
        tags.add(customEnums.NEUTRAL);
        tags.add(customEnums.MELEE);
        tags.add(customEnums.SHIELD);


    }


    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int vigorAmount = 0;
        if (p.hasPower(VigorPower.POWER_ID)) {
            vigorAmount = p.getPower(VigorPower.POWER_ID).amount;
        }
        AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, this.block+vigorAmount));
        AbstractCreature target = FrontTargeting.getTarget(this);
        if (target != null) {
            AbstractDungeon.actionManager.addToBottom(new DamageAction(target,
                new DamageInfo(p, this.damage, this.damageTypeForTurn),
                AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        }
        System.out.println("TYPE DEGATS " + this.damageTypeForTurn);
        super.use(p, m);
    }

    @Override
    public AbstractCard makeCopy() {
        logger.info("RockSmashCOPY---------------------------------------------------------------------------------------------------------------------------------\n\n");
        return new RockSmash();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(UPGRADE_PLUS_DMG);
            this.upgradeBlock(UPGRADE_PLUS_BLOCK);
        }
    }
}
