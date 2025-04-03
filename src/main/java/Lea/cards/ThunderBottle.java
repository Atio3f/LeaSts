package Lea.cards;

import Lea.Abstracts.CrosscodeCard;
import Lea.Abstracts.CrosscodeDamageInfo;
import Lea.characters.Lea;
import Lea.enums.TypeDegats;
import Lea.enums.customEnums;
import Lea.patches.AbstractCardEnum;
import Lea.powers.JoltPower;
import Lea.powers.TargetPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.PotionBounceEffect;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ThunderBottle extends CrosscodeCard {
    public static final String ID = "leacrosscode:ThunderBottle";
    private static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

    // Get object containing the strings that are displayed in the game.
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/Lea/cards/VPRCharge.png";    //IMAGE A CHANGER
    private static final int COST = 1;
    private static final int SP_COST = 0;
    private static final int SP_GAIN = 1;
    private static final int JOLT_VALUE = 3;
    private static final int UPGRAGE_PLUS_JOLT = 1;

    private static final int HIT_AMT = 2;

    private static final TypeDegats TYPE_DEGATS = TypeDegats.NEUTRAL;
    public static final Logger logger = LogManager.getLogger(Lea.class.getName());
    public ThunderBottle() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
            AbstractCard.CardType.ATTACK, AbstractCardEnum.LEA_COBALT,
            CardRarity.COMMON, CardTarget.NONE, SP_COST, SP_GAIN);
        logger.info("ThunderBottle" + DESCRIPTION + "---------------------------------------------------------------------------------------------------------------------------------\n\n");
        this.magicNumber = this.baseMagicNumber = HIT_AMT;


        tags.add(customEnums.SHOCK);


    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {


        for(int i=0;i<HIT_AMT;i++){
            m = AbstractDungeon.getRandomMonster();
            if (m != null) {
                this.addToBot(new VFXAction(new PotionBounceEffect(p.hb.cX, p.hb.cY, m.hb.cX, this.hb.cY), 0.4F));
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, p, new JoltPower(m, p, this.magicNumber), this.magicNumber, true, AbstractGameAction.AttackEffect.NONE));
            }
        }

        System.out.println("TYPE DEGATS " + this.damageTypeForTurn);
        super.use(p, m);

    }

    @Override
    public AbstractCard makeCopy() {
        logger.info("ThunderBottleCOPY---------------------------------------------------------------------------------------------------------------------------------\n\n");
        return new ThunderBottle();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(UPGRAGE_PLUS_JOLT);
        }
    }
}
