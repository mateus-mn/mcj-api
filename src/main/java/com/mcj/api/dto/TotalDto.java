package com.mcj.api.dto;

public class TotalDto
{
	private int total;
	private int totalAtivos;
	private int totalInativos;

	public TotalDto (int total, int totalAtivos, int totalInativos)
	{
		this.total         = total;
		this.totalAtivos   = totalAtivos;
		this.totalInativos = totalInativos;
	}

	public int getTotal()
	{
		return this.total;
	}

	public int getTotalAtivos()
	{
		return this.totalAtivos;
	}

	public int getTotalInativos()
	{
		return this.totalInativos;
	}
}
