package Lea.powers;

import Lea.Abstracts.CrosscodeDamageInfo;
import Lea.characters.Lea;
import Lea.enums.customEnums;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.defect.LightningOrbEvokeAction;
import com.megacrit.cardcrawl.actions.unique.PoisonLoseHpAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.PoisonPower;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import org.apache.logging.log4j.LogManager;

public class JoltPower  extends AbstractPower {
    public static final String POWER_ID = "leacrosscode:JoltPower";
    public static final String NAME = "Jolt";
    public static final String[] DESCRIPTIONS = new String[] {
        "At the beginning of its turn, take",
        " damage.",
        "Double shock damage. -",
            " at the start of your turn." //" 20% actual HP, double shock damage",
    };
    //"At the beginning of its turn, take" +
    //        " 2 damage/stack. If stacks >5, double shock damage. -" +
    //            "33% par tour(min -2)" //" 20% actual HP, double shock damage",
    private AbstractCreature source = null;

    private boolean justApplied = false;
    private static final float EFFECTIVENESS = 2.0F; //Comme pour Vulnerable

    public JoltPower(AbstractCreature owner, AbstractCreature source, int amount) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.source = source;

        if(this.owner != null && !this.owner.isPlayer && this.source != null && this.source.isPlayer && AbstractDungeon.player.hasRelic("leacrosscode:ConductiveRock")){
            Lea.logger.info("PAS MALLLLLLLL ConductiveRock marche -------------------------------");
            this.amount = amount  * 2;
        }else{
            this.amount = amount;
        }
        updateDescription();
        this.type = PowerType.DEBUFF;
        this.isTurnBased = true;
        this.priority = 20;
        this.img = new Texture("img/Lea/powers/JoltPower.png");
        if (AbstractDungeon.actionManager.turnHasEnded) {
            this.justApplied = true;
        }
    }


    @Override
    public void atStartOfTurn() {
        //Semblable au poison
        if (AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT && !AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
            this.flashWithoutSound();
            this.owner.damage(new DamageInfo(this.owner, this.amount * 2, DamageInfo.DamageType.HP_LOSS));
        }
    }



    @Override
    public void atEndOfRound() {
        if (this.justApplied) {
            this.justApplied = false;
        } else {
            if (this.amount < 2) {
                this.addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, "leacrosscode:JoltPower"));
            } else {
                if (this.amount < 6){
                    this.addToBot(new ReducePowerAction(this.owner, this.owner, "leacrosscode:JoltPower", 2));
                }
                this.addToBot(new ReducePowerAction(this.owner, this.owner, "leacrosscode:JoltPower", this.amount / 3));
            }
        }
    }

    @Override
    public float atDamageReceive(float damage, DamageInfo.DamageType type) {
        return damage; //Si il n'y a pas d'indications du type d'attaque on ne change rien des dégâts
    }

    @Override
    public float atDamageReceive(float damage, DamageInfo.DamageType damageType, AbstractCard card) {
        if (card.tags.contains(customEnums.SHOCK) && this.amount > 5) {
            Lea.logger.info("DOUBLE DEGÂTS : "+ damage);
            return damage * 2;
        } else {
            return damage;
        }
    }


    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + (this.amount * 2) + DESCRIPTIONS[1];
        if(amount >= 5){
            description += DESCRIPTIONS[2];
        }
        description += amount == 1 ? "-1" : amount < 6 ? "-2" : (amount / 3);
        description += DESCRIPTIONS[3];
    }

}
