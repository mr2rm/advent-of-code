import fs from "fs/promises";

function solve(ranges: string[], isInvalidId: (id: number) => boolean) {
  const invalidIds: number[] = [];
  ranges.forEach((range) => {
    const [start, end] = range.split("-").map(Number);
    for (let id = start!; id <= end!; id++) {
      if (isInvalidId(id)) {
        invalidIds.push(id);
      }
    }
  });
  console.log(invalidIds.reduce((sum, id) => sum + id));
}

function partOne(ranges: string[]) {
  let isInvalidId = (id: number) => {
    const stringId = id.toString();

    // NOTE: Approach 1: Check for adjacent identical digits
    // const left = stringId.slice(0, stringId.length / 2);
    // const right = stringId.slice(stringId.length / 2);
    // return left === right;

    // NOTE: Approach 2: Regex
    return /^(\d+)\1$/.test(stringId);
  };
  solve(ranges, isInvalidId);
}

function partTwo(ranges: string[]) {
  let isInvalidId = (id: number) => {
    return /^(\d+)\1+$/.test(id.toString());
  };
  solve(ranges, isInvalidId);
}

const lines = await fs.readFile("./input.txt", "utf-8");
let ranges: string[] = lines.split(",");
// partOne(ranges);
partTwo(ranges);
