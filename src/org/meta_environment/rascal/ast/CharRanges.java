package org.meta_environment.rascal.ast;
import org.eclipse.imp.pdb.facts.ITree;
public abstract class CharRanges extends AbstractAST
{
  static public class Range extends CharRanges
  {
/* range:CharRange -> CharRanges {cons("Range")} */
    private Range ()
    {
    }
    /*package */ Range (ITree tree,
			org.meta_environment.rascal.ast.CharRange range)
    {
      this.tree = tree;
      this.range = range;
    }
    public IVisitable accept (IASTVisitor visitor)
    {
      return visitor.visitCharRangesRange (this);
    }
    private org.meta_environment.rascal.ast.CharRange range;
    public org.meta_environment.rascal.ast.CharRange getRange ()
    {
      return range;
    }
    private void $setRange (org.meta_environment.rascal.ast.CharRange x)
    {
      this.range = x;
    }
    public Range setRange (org.meta_environment.rascal.ast.CharRange x)
    {
      Range z = new Range ();
      z.$setRange (x);
      return z;
    }
  }
  static public class Ambiguity extends CharRanges
  {
    private final java.util.List <
      org.meta_environment.rascal.ast.CharRanges > alternatives;
    public Ambiguity (java.util.List <
		      org.meta_environment.rascal.ast.CharRanges >
		      alternatives)
    {
      this.alternatives =
	java.util.Collections.unmodifiableList (alternatives);
    }
    public java.util.List < org.meta_environment.rascal.ast.CharRanges >
      getAlternatives ()
    {
      return alternatives;
    }
  }
  static public class Concatenate extends CharRanges
  {
/* lhs:CharRanges rhs:CharRanges -> CharRanges {cons("Concatenate"), right, memo} */
    private Concatenate ()
    {
    }
    /*package */ Concatenate (ITree tree,
			      org.meta_environment.rascal.ast.CharRanges lhs,
			      org.meta_environment.rascal.ast.CharRanges rhs)
    {
      this.tree = tree;
      this.lhs = lhs;
      this.rhs = rhs;
    }
    public IVisitable accept (IASTVisitor visitor)
    {
      return visitor.visitCharRangesConcatenate (this);
    }
    private org.meta_environment.rascal.ast.CharRanges lhs;
    public org.meta_environment.rascal.ast.CharRanges getLhs ()
    {
      return lhs;
    }
    private void $setLhs (org.meta_environment.rascal.ast.CharRanges x)
    {
      this.lhs = x;
    }
    public Concatenate setLhs (org.meta_environment.rascal.ast.CharRanges x)
    {
      Concatenate z = new Concatenate ();
      z.$setLhs (x);
      return z;
    }
    private org.meta_environment.rascal.ast.CharRanges rhs;
    public org.meta_environment.rascal.ast.CharRanges getRhs ()
    {
      return rhs;
    }
    private void $setRhs (org.meta_environment.rascal.ast.CharRanges x)
    {
      this.rhs = x;
    }
    public Concatenate setRhs (org.meta_environment.rascal.ast.CharRanges x)
    {
      Concatenate z = new Concatenate ();
      z.$setRhs (x);
      return z;
    }
  }
  static public class Bracket extends CharRanges
  {
/* "(" CharRanges ")" -> CharRanges {bracket} */
    private Bracket ()
    {
    }
    /*package */ Bracket (ITree tree)
    {
      this.tree = tree;
    }
    public IVisitable accept (IASTVisitor visitor)
    {
      return visitor.visitCharRangesBracket (this);
    }
  }
}
