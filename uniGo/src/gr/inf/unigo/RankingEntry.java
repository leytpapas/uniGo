package gr.inf.unigo;

public class RankingEntry
{
    String userName;
    int xp;
    int rank;

    public RankingEntry( String userName, int xp )
    {
        this.userName = userName;
        this.xp = xp;
        this.rank = -1;
    }

    public int getRank()
    {
        return rank;
    }

    public void setRank( int rank )
    {
        this.rank = rank;
    }

    public int getXp()
    {
        return xp;
    }

    public void setXp( int xp )
    {
        this.xp = xp;
    }

    public String getUserName()
    {
        return userName;
    }

    public void setUserName( String userName )
    {
        this.userName = userName;
    }



}
