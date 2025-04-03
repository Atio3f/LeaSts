package Lea.cards;

import Lea.Abstracts.CrosscodeCard;
import Lea.Abstracts.CrosscodeCharacter;
import Lea.characters.Lea;
import Lea.enums.customEnums;
import Lea.patches.AbstractCardEnum;
import Lea.powers.powereffect.LimitlessSpamPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

//Carte Amplificateur vocal Coût 1 énergie : +3 Wave Gain 2(4) Strength
public class VoiceAmplifier extends CrosscodeCard {
    public static final String ID = "leacrosscode:VoiceAmplifier";
    private static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

    // Get object containing the strings that are displayed in the game.
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String UPGRADE_DESC = cardStrings.UPGRADE_DESCRIPTION;
    public static final String IMG_PATH = "img/Lea/cards/Dash.png"; //IMAGE A MODIFIER
    private static final int COST = 1;

    private static final int UPGRADE_PLUS_BULLET = 1;   //Génère une BULLET dans la main quand upgrade
    private static final int SP_COST = 0;
    private static final int SP_GAIN = 0;

    private static final int STRENGTH_AMT = 2;
    private static final int UPGRADE_PLUS_STRENGTH = 2;


    public static final Logger logger = LogManager.getLogger(Lea.class.getName());
    public VoiceAmplifier() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
            AbstractCard.CardType.POWER, AbstractCardEnum.LEA_COBALT,
            CardRarity.UNCOMMON, CardTarget.SELF, SP_COST, SP_GAIN);
        logger.info("VoiceAmplifier" + DESCRIPTION + "---------------------------------------------------------------------------------------------------------------------------------\n\n");
        magicNumber = baseMagicNumber = STRENGTH_AMT;
        tags.add(customEnums.WAVE);
        this.exhaust = true;

    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new StrengthPower(p, this.magicNumber), this.magicNumber, true, AbstractGameAction.AttackEffect.NONE));
        //+3 Wave
        if(p.getClass().getSuperclass().getSimpleName().equals("CrosscodeCharacter")){
            ((CrosscodeCharacter)p).gainElements(new Integer[]{0, 0, 0, 3});
        }
        super.use(p, m);
    }

    @Override
    public AbstractCard makeCopy() {
        logger.info("VoiceAmplifierCOPY---------------------------------------------------------------------------------------------------------------------------------\n\n");
        return new VoiceAmplifier();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeMagicNumber(UPGRADE_PLUS_STRENGTH);
            this.upgradeName();
            this.rawDescription = UPGRADE_DESC;
        }
    }
}
