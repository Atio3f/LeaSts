package Lea.cards.arts.T3;

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
//Homing 4 damage for each Bullet type cards used this fight(like a bullet) Art lvl 3 -> on va faire en fonction du nombre de cartes BULLET
public class OneMoreTime extends CrosscodeCard {
    public static final String ID = "leacrosscode:OneMoreTime";
    private static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

    // Get object containing the strings that are displayed in the game.
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/Lea/cards/VPRCharge.png";        //IMAGE A CHANGER
    private static final int COST = 3;
    private static final int UPGRADE_COST = 2;
    private static final int ATTACK_DMG = 3;    //Nerf to 1 was 3 beforge and 4 in first
    private static final int UPGRADE_PLUS_DMG = 0;
    private static final int SP_COST = 8;
    private static final int SP_GAIN = 0;
    private static final int NUMBER_HITS = 1;

    private static final TypeDegats TYPE_DEGATS = TypeDegats.NEUTRAL;
    public static final Logger logger = LogManager.getLogger(Lea.class.getName());
    public OneMoreTime() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
            AbstractCard.CardType.ATTACK, AbstractCardEnum.LEA_COBALT,
            customEnums.COMBAT_ART_T3, AbstractCardEnum.HOMING, SP_COST, SP_GAIN);
        logger.info("OneMoreTime" + DESCRIPTION + "---------------------------------------------------------------------------------------------------------------------------------\n\n");
        this.damage=this.baseDamage = ATTACK_DMG;
        this.magicNumber = this.baseMagicNumber = NUMBER_HITS;
        this.exhaust = true;

        tags.add(customEnums.NEUTRAL);
        tags.add(customEnums.BULLET);
        tags.add(customEnums.COMBAT_ART);
        tags.add(CardTags.HEALING); //Permet d'empêcher d'obtenir la carte via dead branch
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        //Pas encore fait frérot

        int bulletCount = 0;
        for (AbstractCard c : AbstractDungeon.actionManager.cardsPlayedThisCombat) {
            if (c.tags.contains(customEnums.BULLET)) {
                bulletCount++;
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
        }
        System.out.println("TYPE DEGATS " + this.damageTypeForTurn);
        super.use(p, m);

    }

    @Override
    public AbstractCard makeCopy() {
        logger.info("OneMoreTimeCOPY---------------------------------------------------------------------------------------------------------------------------------\n\n");
        return new OneMoreTime();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBaseCost(UPGRADE_COST);
        }
    }
}
