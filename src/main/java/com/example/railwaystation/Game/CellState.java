package com.example.railwaystation.Game;

//потрібно щоб знати чи можна сатвати на певну клітинку
public enum CellState {
    DOOR,
    CASH_REGISTER_PART, //чатина каса, бо коли закривається, то закривається не 1 клітинка а вся каса
    QUEUE,
    EMPTY,
    SOLID //зайнята клітинка, але ще не знаємо причину
}
