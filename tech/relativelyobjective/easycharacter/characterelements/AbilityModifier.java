package tech.relativelyobjective.easycharacter.characterelements;

import tech.relativelyobjective.easycharacter.utilities.Lists;

/**
 *
 * @author ReltivlyObjectv
 */
public class AbilityModifier implements CharacterElement {
	public Lists.Ability ability;
	public Integer modifier;
	
	public AbilityModifier() {
		ability = null;
		modifier = null;
	}
	@Override
	public void edit() {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}
}
