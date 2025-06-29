package Lea.powers;

import Lea.characters.Lea;
import Lea.enums.customEnums;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;
//Protection get nerf by 1 flat reduction
public class ProtectionPower extends AbstractPower {
    public static final String POWER_ID = "leacrosscode:ProtectionPower";
    public static final String NAME = "Protection";
    public static final String[] DESCRIPTIONS = new String[]{
        "Reduce damage takes by attacks per !S!."   //!S! remplacé par stacks (+ 1) dans la fonction updateDescription
    };
    public static final String[] DESCRIPTIONS_HEXA = new String[]{
        "Gain ",
        " Block at the end of your turn."
    };
    private boolean justApplied = false;
    private static final int BASE_VALUE = 0; //Valeur de base de dégâts réduits sans compter le nombre de stacks c'était 1 avant mdr mais trop fort

    public ProtectionPower(AbstractCreature owner, int amount) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        updateDescription();
        this.type = PowerType.BUFF;
        this.canGoNegative = true;
        this.isTurnBased = true;
        this.priority = 80;
        this.img = new Texture("img/Lea/powers/ProtectionPower.png");
        if (AbstractDungeon.actionManager.turnHasEnded) {
            this.justApplied = true;
        }
    }

    @Override
    public void atStartOfTurn() {
        if(owner.isPlayer && owner.hasPower("leacrosscode:QuadroguardStylePower")) {
            int reduceAmount = owner.getPower("leacrosscode:ProtectionPower").amount / owner.getPower("leacrosscode:QuadroguardStylePower").amount;
            addToBot(new ReducePowerAction(owner, owner, "leacrosscode:ProtectionPower", reduceAmount > 1 ? reduceAmount : 1 ));

        }else{
            this.addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, "leacrosscode:ProtectionPower"));
        }
    }

    @Override
    public void atEndOfTurn(boolean isPlayer){
        if(this.owner != null && this.owner.isPlayer && owner.hasPower("leacrosscode:HexacastStylePower")){
            addToTop(new GainBlockAction(owner, amount));
        }
    }
    public float atDamageReceive(float damage, DamageInfo.DamageType type) {
        float damageFinal = damage ;//> (BASE_VALUE + amount) ? damage - (BASE_VALUE + amount) : 0;

        return damageFinal;
    }

    public float atDamageReceive(float damage, DamageInfo.DamageType damageType, AbstractCard card) {
        float damageFinal = damage ;//> (BASE_VALUE + amount) ? damage - (BASE_VALUE + amount) : 0;

        return damageFinal;
        //return damage;
    }

    public float atDamageFinalReceive(float damage, DamageInfo.DamageType damageType, AbstractCard card) {
        float damageFinal = damage > (BASE_VALUE + amount) ? damage - (BASE_VALUE + amount) : 0;
        /*if(this.owner != null && this.owner.isPlayer && AbstractDungeon.player.hasRelic("leacrosscode:MartialCounterAttack")){
            int amountVigor = (int) (damage - damageFinal); // Si le joueur a la relique Counter Playstyle, il gagne de la vigueur pour chaque dégât absorbé
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.owner,
                this.owner, new VigorPower(this.owner, amountVigor), amountVigor, true, AbstractGameAction.AttackEffect.BLUNT_LIGHT));

        }*/
        return damage;
        //return damage;
    }

    public int onAttacked(DamageInfo info, int damageAmount) {
        if(this.owner != null && this.owner.isPlayer && owner.hasPower("leacrosscode:HexacastStylePower")){
            return damageAmount;
        }
        int damageFinal = damageAmount > (BASE_VALUE + amount) ? damageAmount - (BASE_VALUE + amount) : 0;
        if(this.owner != null && this.owner.isPlayer && AbstractDungeon.player.hasRelic("leacrosscode:MartialCounterAttack")){
            int amountVigor = (int) (damageAmount - damageFinal); // Si le joueur a la relique Counter Playstyle, il gagne de la vigueur pour chaque dégât absorbé
            Lea.logger.info("DEGATS : "+ damageAmount + " FINAUX : "+damageFinal + " ; VIGUEUR : " + amountVigor);
            if(amountVigor>0) {
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.owner,
                    this.owner, new VigorPower(this.owner, amountVigor), amountVigor, true, AbstractGameAction.AttackEffect.BLUNT_LIGHT));
            }

        }
        return damageFinal;
    }

    @Override
    public void updateDescription() {
        //Protection doesn't have the same effect when player have the HexacastStylePower
        if(this.owner != null && this.owner.isPlayer && AbstractDungeon.player.hasPower("leacrosscode:HexacastStylePower")){
            this.description = DESCRIPTIONS_HEXA[0] + amount + DESCRIPTIONS_HEXA[1];
        }else{
            this.description = DESCRIPTIONS[0].replace("!S!", Integer.toString(this.amount + BASE_VALUE));
        }

    }
}
