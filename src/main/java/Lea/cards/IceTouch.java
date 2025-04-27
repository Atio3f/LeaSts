package Lea.cards;

import Lea.Abstracts.CrosscodeCard;
import Lea.Abstracts.FrontTargeting;
import Lea.characters.Lea;
import Lea.enums.TypeDegats;
import Lea.enums.customEnums;
import Lea.patches.AbstractCardEnum;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

//Cost 3 : Consume all your Cold and deal 18(26) cold damage to an ennemi. Apply 1 chill per cold consumed. (p-ê sinon 4(5) dégâts et 1 chill par cold consumed
public class IceTouch extends CrosscodeCard {
    public static final String ID = "leacrosscode:IceTouch";
    private static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

    // Get object containing the strings that are displayed in the game.
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/Lea/cards/Hit.png";
    private static final int COST = 3;
    private static final int ATTACK_DMG = 18;
    private static final int UPGRADE_PLUS_DMG = 8;
    private static final int SP_COST = 0;
    private static final int SP_GAIN = 0;

    private static final TypeDegats TYPE_DEGATS = TypeDegats.ICE;
    public static final Logger logger = LogManager.getLogger(Lea.class.getName());
    public IceTouch() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
            AbstractCard.CardType.ATTACK, AbstractCardEnum.LEA_COBALT,
            CardRarity.UNCOMMON, CardTarget.ENEMY , SP_COST, SP_GAIN);
        logger.info("IceTouch" + DESCRIPTION + "---------------------------------------------------------------------------------------------------------------------------------\n\n");
        this.damage=this.baseDamage = ATTACK_DMG;

        tags.add(CardTags.STARTER_STRIKE); //This tag marks it as a basic Strike
        tags.add(CardTags.STRIKE); //This tag marks it as a Strike card for the purposes of Perfected Strike and any similar modded effects
        tags.add(customEnums.NEUTRAL);
        tags.add(customEnums.MELEE);


    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new DamageAction(m,
            new DamageInfo(p, this.damage, this.damageTypeForTurn),
            AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        System.out.println("TYPE DEGATS " + this.damageTypeForTurn);
        super.use(p, m);
    }

    @Override
    public AbstractCard makeCopy() {
        logger.info("IceTouchCOPY---------------------------------------------------------------------------------------------------------------------------------\n\n");
        return new IceTouch();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(UPGRADE_PLUS_DMG);
        }
    }
}

