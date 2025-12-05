import fs from "fs/promises";

function isInvalidId(id: number) {
  const stringId = id.toString();

  // NOTE: Approach 1: Check for adjacent identical digits
  // const left = stringId.slice(0, stringId.length / 2);
  // const right = stringId.slice(stringId.length / 2);
  // return left === right;

  // NOTE: Approach 2: Regex
  return /^(\d+)\1$/.test(stringId);
}

function partOne(ranges: string[]) {
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

const lines = await fs.readFile("./input.txt", "utf-8");
partOne(lines.split(","));
