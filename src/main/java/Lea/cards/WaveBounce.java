package Lea.cards;

import Lea.Abstracts.CrosscodeCard;
import Lea.Abstracts.CrosscodeDamageInfo;
import Lea.characters.Lea;
import Lea.enums.customEnums;
import Lea.patches.AbstractCardEnum;
import Lea.powers.MarkPower;
import Lea.powers.TargetPower;
import com.evacipated.cardcrawl.mod.stslib.actions.common.AllEnemyApplyPowerAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class WaveBounce extends CrosscodeCard {
    public static final String ID = "leacrosscode:WaveBounce";
    private static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

    // Get object containing the strings that are displayed in the game.
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/Lea/cards/Dash.png"; //IMAGE A CHANGER
    private static final int COST = 1;
    private static final int ATTACK_DMG = 4;    //NERF it was 5 maybe it's too much
    private static final int MARK_AMT = 2;
    private static final int MULTIPLIER = 140;  //NERF it was 150
    private static final int UPGRADE_PLUS_MULTIPLIER = 40;  //NERF it was 50
    private static final int DIVIDER = 100; //We can't assign a float on magicNumber
    private static final int SP_COST = 0;
    private static final int SP_GAIN = 1;
    private static final int WAVE_COST = 1;

    public static final Logger logger = LogManager.getLogger(Lea.class.getName());
    public WaveBounce() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
            CardType.ATTACK, AbstractCardEnum.LEA_COBALT,
            CardRarity.UNCOMMON, CardTarget.ALL_ENEMY, SP_COST, SP_GAIN, WAVE_COST);
        logger.info("WaveBounce" + DESCRIPTION + "---------------------------------------------------------------------------------------------------------------------------------\n\n");
        this.damage = this.baseDamage = ATTACK_DMG;
        this.magicNumber = this.baseMagicNumber = MULTIPLIER;

        ElementCard = "Wave";
        tags.add(customEnums.WAVE);
        tags.add(customEnums.BULLET);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        float multiplier = 1;
        for(AbstractMonster monster : AbstractDungeon.getMonsters().monsters){
            if(!monster.isDeadOrEscaped()){
                AbstractDungeon.actionManager.addToBottom(new DamageAction(monster,
                    new CrosscodeDamageInfo(p, (int) (this.damage * multiplier), this.damageTypeForTurn, tags),
                    AbstractGameAction.AttackEffect.SMASH));
                addToBot(new ApplyPowerAction(monster, p, new MarkPower(monster, (int) (MARK_AMT * multiplier))));
                multiplier += (magicNumber / DIVIDER);
            }
        }

        super.use(p, m);
    }

    @Override
    public AbstractCard makeCopy() {
        logger.info("WaveBounceCOPY---------------------------------------------------------------------------------------------------------------------------------\n\n");
        return new WaveBounce();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(UPGRADE_PLUS_MULTIPLIER);
        }
    }
}
