package Lea.cards.words;

import Lea.Abstracts.CrosscodeCard;
import Lea.characters.Lea;
import Lea.enums.TypeDegats;
import Lea.enums.customEnums;
import Lea.patches.AbstractCardEnum;
import Lea.powers.GainSPNextTurnPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DrawCardNextTurnPower;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import com.megacrit.cardcrawl.vfx.combat.CleaveEffect;
import com.megacrit.cardcrawl.vfx.combat.WhirlwindEffect;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Bye extends CrosscodeCard {
    public static final String ID = "leacrosscode:Bye";
    private static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

    // Get object containing the strings that are displayed in the game.
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/Lea/cards/Wait.png";     //IMAGE A CHANGER
    private static final int COST = -1;
    private static final int CARD_DRAW = 1;
    private static final int UPGRADE_PLUS_CARD = 1;
    private static final int SP_COST = 0;
    private static final int SP_GAIN = 0;



    private static final TypeDegats TYPE_DEGATS = TypeDegats.NEUTRAL;
    public static final Logger logger = LogManager.getLogger(Lea.class.getName());
    public Bye() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
            AbstractCard.CardType.SKILL, AbstractCardEnum.LEA_COBALT,
            CardRarity.UNCOMMON, CardTarget.SELF, SP_COST, SP_GAIN);
        logger.info("Bye" + DESCRIPTION + "---------------------------------------------------------------------------------------------------------------------------------\n\n");
        this.magicNumber = this.baseMagicNumber = CARD_DRAW;


        tags.add(customEnums.NEUTRAL);
        this.exhaust = true;

    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int effect = EnergyPanel.totalCount;
        if (this.energyOnUse != -1) {
            effect = this.energyOnUse;
        }

        if (p.hasRelic("Chemical X")) {
            effect += 2;
            p.getRelic("Chemical X").flash();
        }


        if(upgraded){
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new DrawCardNextTurnPower(p, effect * 2 != 0 ? effect * 2 : 1)));  //Condition ternaire parce que le minimum de cartes piochées est 1.
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new GainSPNextTurnPower(p, effect + 2)));
        }else{
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new DrawCardNextTurnPower(p, effect + 1)));
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new GainSPNextTurnPower(p, effect + 1)));
        }
        if (!this.freeToPlayOnce) {
            p.energy.use(EnergyPanel.totalCount);
        }
        AbstractDungeon.actionManager.addToBottom(new EndTurnAction());
        super.use(p, m);
    }

    @Override
    public AbstractCard makeCopy() {
        logger.info("ByeCOPY---------------------------------------------------------------------------------------------------------------------------------\n\n");
        return new Bye();
    }

    @Override
    public void update() {
        super.update();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(UPGRADE_PLUS_CARD);
        }
    }
}
