package it.battagliandrea.formula1.domain.models

fun List<Result>.getResultByPosition(position: Int) =
    this.firstOrNull { it.position == position }

fun List<Result>.toPodium(): Podium =
    Podium(
        first = this.getResultByPosition(position = 1),
        second = this.getResultByPosition(position = 2),
        third = this.getResultByPosition(position = 3),
    )
