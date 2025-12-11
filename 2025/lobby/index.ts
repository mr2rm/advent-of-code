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

function partTwo(banks: string[]) {
  let total = 0;
  banks.forEach((bank) => {
    const values = bank.split("").map(Number);

    let maxIndex = 0;
    const digits: number[] = [];
    for (let needed = 11; needed >= 0; needed--) {
      for (let i = maxIndex + 1; i < values.length - needed; i++) {
        if (values[i]! > values[maxIndex]!) {
          maxIndex = i;
        }
      }
      digits.push(values[maxIndex]!);
      maxIndex++;
    }

    total += Number(digits.join(""));
  });

  console.log(total);
}

const lines = await fs.readFile("./input.txt", "utf-8");
const banks: string[] = lines.trimEnd().split("\n");
// partOne(banks);
partTwo(banks);
