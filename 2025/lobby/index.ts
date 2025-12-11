import fs from "fs/promises";

function partOne(banks: string[]) {
  let total = 0;
  banks.forEach((bank) => {
    const values = bank.split("").map(Number);

    let maxIndex = 0;
    for (let i = 0; i < values.length - 1; i++) {
      if (values[i]! > values[maxIndex]!) {
        maxIndex = i;
      }
    }

    let nextMaxIndex = maxIndex + 1;
    for (let i = nextMaxIndex; i < values.length; i++) {
      if (values[i]! > values[nextMaxIndex]!) {
        nextMaxIndex = i;
      }
    }

    const joltage = values[maxIndex]! * 10 + values[nextMaxIndex]!;
    total += joltage;
  });
  console.log(total);
}

const lines = await fs.readFile("./input.txt", "utf-8");
const banks: string[] = lines.trimEnd().split("\n");
partOne(banks);
