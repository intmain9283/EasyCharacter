package tech.relativelyobjective.easycharacter.races;

import tech.relativelyobjective.easycharacter.characterelements.AbilityModifier;
import tech.relativelyobjective.easycharacter.characterelements.DamageModifier;
import tech.relativelyobjective.easycharacter.characterelements.Language;
import tech.relativelyobjective.easycharacter.characterelements.Race;
import tech.relativelyobjective.easycharacter.characterelements.Size;
import tech.relativelyobjective.easycharacter.characterelements.WalkSpeed;
import tech.relativelyobjective.easycharacter.utilities.InformationManager;
import tech.relativelyobjective.easycharacter.utilities.Lists;
import tech.relativelyobjective.easycharacter.utilities.WindowManager;

/**
 *
 * @author ReltivlyObjectv
 */
public class Breton {
	public static void setup() {
		InformationManager.resetRaceElements();
		InformationManager.addRaceElement(new Race("Breton"));
		InformationManager.addRaceElement(new Size(Lists.Size.MEDIUM));
		InformationManager.addRaceElement(new WalkSpeed(30));
		InformationManager.addRaceElement(new Language("Cyrodilic"));
		InformationManager.addRaceElement(new AbilityModifier(Lists.Ability.INTELLIGENCE,2));
		InformationManager.addRaceElement(new AbilityModifier(Lists.Ability.WISDOM,2));
		InformationManager.addRaceElement(
			new DamageModifier("Lightning (from magical sources)", Lists.DamageMod.RESISTANT)
		);
		InformationManager.addRaceElement(
			new DamageModifier("Fire (from magical sources)", Lists.DamageMod.RESISTANT)
		);
		InformationManager.addRaceElement(
			new DamageModifier("Cold (from magical sources)", Lists.DamageMod.RESISTANT)
		);
		WindowManager.getRaceTab().updateRaceElementsList();
	}
}
