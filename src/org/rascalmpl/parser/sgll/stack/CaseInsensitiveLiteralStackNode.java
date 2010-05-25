package org.rascalmpl.parser.sgll.stack;

import org.eclipse.imp.pdb.facts.IConstructor;
import org.rascalmpl.parser.sgll.result.INode;
import org.rascalmpl.parser.sgll.result.LiteralNode;

public final class CaseInsensitiveLiteralStackNode extends AbstractStackNode{
	private final IConstructor symbol;
	private final char[][] ciLiteral;
	
	private LiteralNode result;
	
	public CaseInsensitiveLiteralStackNode(int id, IConstructor symbol, char[] ciLiteral){
		super(id);
		
		this.symbol = symbol;
		
		int nrOfCharacters = ciLiteral.length;
		this.ciLiteral = new char[nrOfCharacters][];
		for(int i = nrOfCharacters - 1; i >= 0; i--){
			char character = ciLiteral[i];
			int type = Character.getType(character);
			if(type == Character.LOWERCASE_LETTER){
				this.ciLiteral[i] = new char[]{character, Character.toUpperCase(character)};
			}else if(type == Character.UPPERCASE_LETTER){
				this.ciLiteral[i] = new char[]{character, Character.toLowerCase(character)};
			}else{
				this.ciLiteral[i] = new char[]{character};
			}
		}
	}
	
	private CaseInsensitiveLiteralStackNode(CaseInsensitiveLiteralStackNode contextInsensitiveLiteralParseStackNode){
		super(contextInsensitiveLiteralParseStackNode);
		
		symbol = contextInsensitiveLiteralParseStackNode.symbol;
		ciLiteral = contextInsensitiveLiteralParseStackNode.ciLiteral;
		
		result = null;
	}
	
	public boolean isReducable(){
		return true;
	}
	
	public boolean isList(){
		return false;
	}
	
	public boolean isEpsilon(){
		return false;
	}
	
	public String getMethodName(){
		throw new UnsupportedOperationException();
	}
	
	public boolean reduce(char[] input){
		int literalLength = ciLiteral.length;
		char[] resultLiteral = new char[literalLength];
		OUTER : for(int i = literalLength - 1; i >= 0; i--){
			char[] ciLiteralPart = ciLiteral[i];
			for(int j = ciLiteralPart.length - 1; j >= 0; j--){
				char character = ciLiteralPart[j];
				if(character == input[startLocation + i]){
					resultLiteral[i] = character;
					continue OUTER;
				}
			}
			return false; // Did not match.
		}
		
		result = new LiteralNode(symbol, resultLiteral);
		return true;
	}
	
	public AbstractStackNode getCleanCopy(){
		return new CaseInsensitiveLiteralStackNode(this);
	}
	
	public AbstractStackNode getCleanCopyWithPrefix(){
		CaseInsensitiveLiteralStackNode cilpsn = new CaseInsensitiveLiteralStackNode(this);
		cilpsn.prefixes = prefixes;
		cilpsn.prefixStartLocations = prefixStartLocations;
		return cilpsn;
	}
	
	public int getLength(){
		return ciLiteral.length;
	}
	
	public void mark(){
		throw new UnsupportedOperationException();
	}
	
	public boolean isMarked(){
		throw new UnsupportedOperationException();
	}
	
	public AbstractStackNode[] getChildren(){
		throw new UnsupportedOperationException();
	}
	
	public void addResult(IConstructor production, INode[] children){
		throw new UnsupportedOperationException();
	}
	
	public INode getResult(){
		return result;
	}
	
	public String toString(){
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < ciLiteral.length; i++){
			sb.append(ciLiteral[i][0]);
		}
		sb.append(getId());
		sb.append('(');
		sb.append(startLocation);
		sb.append(',');
		sb.append(startLocation + getLength());
		sb.append(')');
		
		return sb.toString();
	}
}
