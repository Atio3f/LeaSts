package Lea.cards;

import Lea.Abstracts.CrosscodeCard;
import Lea.Abstracts.CrosscodeDamageInfo;
import Lea.Abstracts.HomingTargeting;
import Lea.characters.Lea;
import Lea.enums.TypeDegats;
import Lea.enums.customEnums;
import Lea.patches.AbstractCardEnum;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DamageRandomEnemyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Bullet extends CrosscodeCard {
    public static final String ID = "leacrosscode:Bullet";
    private static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

    // Get object containing the strings that are displayed in the game.
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/Lea/cards/VPRCharge.png";        //IMAGE A CHANGER
    private static final int COST = 0;
    private static final int ATTACK_DMG = 3;
    private static final int UPGRADE_PLUS_DMG = 2;
    private static final int SP_COST = 0;
    private static final int SP_GAIN = 0;
    private static final int UPGRADE_PLUS_SP_GAIN = 0;
    private static final int NUMBER_HITS = 1;

    private static final TypeDegats TYPE_DEGATS = TypeDegats.NEUTRAL;
    public static final Logger logger = LogManager.getLogger(Lea.class.getName());
    public Bullet() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
            AbstractCard.CardType.ATTACK, AbstractCardEnum.LEA_COBALT,
            CardRarity.SPECIAL, AbstractCardEnum.HOMING, SP_COST, SP_GAIN);
        logger.info("Bullet" + DESCRIPTION + "---------------------------------------------------------------------------------------------------------------------------------\n\n");
        this.damage=this.baseDamage = ATTACK_DMG;
        this.magicNumber = this.baseMagicNumber = NUMBER_HITS;
        this.retain = true;
        this.exhaust = true;

        tags.add(customEnums.NEUTRAL);
        tags.add(customEnums.BULLET);

    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {


        for(int i=0; i<magicNumber;i++) {
            AbstractCreature cible = HomingTargeting.getTarget(this);
            Lea.logger.info("CIBLE : " + cible);
            if(cible == null) {
                AbstractDungeon.actionManager.addToBottom(new DamageRandomEnemyAction(
                    new CrosscodeDamageInfo(p, this.damage, this.damageTypeForTurn, tags),
                    AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
            }else{
                AbstractDungeon.actionManager.addToBottom(new DamageAction(cible,
                    new CrosscodeDamageInfo(p, this.damage, this.damageTypeForTurn, tags),
                    AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
            }
        }


        System.out.println("TYPE DEGATS " + this.damageTypeForTurn);
        super.use(p, m);

    }

    @Override
    public AbstractCard makeCopy() {
        logger.info("BulletCOPY---------------------------------------------------------------------------------------------------------------------------------\n\n");
        return new Bullet();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(UPGRADE_PLUS_DMG);
        }
    }
}
