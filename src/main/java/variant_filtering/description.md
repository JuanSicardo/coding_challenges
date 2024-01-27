Variant Filtering
=================

A variant is a combination of a chromosome, a position, a reference allele and an alternate allele.

For example: `chr8:5:G>C`

A genomic region is an open-ended interval (start is included, end is excluded) with a start and an end position on a
certain chromosome.

For example: `chr8:15-20`

A variant is in a genomic region if it is on the same chromosome, and it's position is contained within the start and
end positions of the genomic region.

For example; given the following variants and the genomic regions:

Variants
--------

* `chr1:5:G>C`
* `chr1:6:A>T`
* `chr1:8:A>C`
* `chr1:15:G>T`
* `chr8:10:C>G`
* `chr8:12:A>G`
* `chr19:3:G>A`
* `chr19:20:C>T`
* `chr19:22:T>A`

Genomic Regions
---------------

* `chr1:6-8`
* `chr19:1-2`
* `chr19:20-25`

The variants that are in the genomic regions are the following:

* `chr1:6:A>T`
* `chr19:20:C>T`
* `chr19:22:T>A`

**Notice that variant `chr1:6:A>T` is inside region `chr1:6-8` but variant `chr1:8:A>C` is not because regions are
open-ended (end position is excluded).**

Please write a program which, given a list of variants and a list of genomic regions in the formats above, outputs the
variants which are at least in one of the genomic regions. You can assume the following about the inputs:

* Variants are in ascending order.
* Variants do not overlap with each other.
* Variants alleles are 1 base pair long.
* Genomic regions are in ascending order.
* Genomic regions do not overlap with each other.


