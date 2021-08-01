(ns vaguely.canned
  )

(def datasets
  '{"trees"
   ({:id 1, :Girth 8.3, :Height 70, :Volume 10.3}
    {:id 2, :Girth 8.6, :Height 65, :Volume 10.3}
    {:id 3, :Girth 8.8, :Height 63, :Volume 10.2}
    {:id 4, :Girth 10.5, :Height 72, :Volume 16.4}
    {:id 5, :Girth 10.7, :Height 81, :Volume 18.8}
    {:id 6, :Girth 10.8, :Height 83, :Volume 19.7}
    {:id 7, :Girth 11, :Height 66, :Volume 15.6}
    {:id 8, :Girth 11, :Height 75, :Volume 18.2}
    {:id 9, :Girth 11.1, :Height 80, :Volume 22.6}
    {:id 10, :Girth 11.2, :Height 75, :Volume 19.9}
    {:id 11, :Girth 11.3, :Height 79, :Volume 24.2}
    {:id 12, :Girth 11.4, :Height 76, :Volume 21}
    {:id 13, :Girth 11.4, :Height 76, :Volume 21.4}
    {:id 14, :Girth 11.7, :Height 69, :Volume 21.3}
    {:id 15, :Girth 12, :Height 75, :Volume 19.1}
    {:id 16, :Girth 12.9, :Height 74, :Volume 22.2}
    {:id 17, :Girth 12.9, :Height 85, :Volume 33.8}
    {:id 18, :Girth 13.3, :Height 86, :Volume 27.4}
    {:id 19, :Girth 13.7, :Height 71, :Volume 25.7}
    {:id 20, :Girth 13.8, :Height 64, :Volume 24.9}
    {:id 21, :Girth 14, :Height 78, :Volume 34.5}
    {:id 22, :Girth 14.2, :Height 80, :Volume 31.7}
    {:id 23, :Girth 14.5, :Height 74, :Volume 36.3}
    {:id 24, :Girth 16, :Height 72, :Volume 38.3}
    {:id 25, :Girth 16.3, :Height 77, :Volume 42.6}
    {:id 26, :Girth 17.3, :Height 81, :Volume 55.4}
    {:id 27, :Girth 17.5, :Height 82, :Volume 55.7}
    {:id 28, :Girth 17.9, :Height 80, :Volume 58.3}
    {:id 29, :Girth 18, :Height 80, :Volume 51.5}
    {:id 30, :Girth 18, :Height 80, :Volume 51}
    {:id 31, :Girth 20.6, :Height 87, :Volume 77})
   "infertility"
   ({:stratum 1, :parity 6, :age 26, :pooled.stratum 3, :induced 1,:__id 1, :case 1, :education "0-5yrs", :spontaneous 2}
    {:stratum 2, :parity 1, :age 42, :pooled.stratum 1, :induced 1, :__id 2, :case 1, :education "0-5yrs", :spontaneous 0}
    {:stratum 3, :parity 6, :age 39, :pooled.stratum 4, :induced 2, :__id 3, :case 1, :education "0-5yrs", :spontaneous 0}
    {:stratum 4, :parity 4, :age 34, :pooled.stratum 2, :induced 2, :__id 4, :case 1, :education "0-5yrs", :spontaneous 0}
    {:stratum 5, :parity 3, :age 35, :pooled.stratum 32, :induced 1, :__id 5, :case 1, :education "6-11yrs", :spontaneous 1}
    {:stratum 6, :parity 4, :age 36, :pooled.stratum 36, :induced 2, :__id 6, :case 1, :education "6-11yrs", :spontaneous 1}
    {:stratum 7, :parity 1, :age 23, :pooled.stratum 6, :induced 0, :__id 7, :case 1, :education "6-11yrs", :spontaneous 0}
    {:stratum 8, :parity 2, :age 32, :pooled.stratum 22, :induced 0, :__id 8, :case 1, :education "6-11yrs", :spontaneous 0}
    {:stratum 9, :parity 1, :age 21, :pooled.stratum 5, :induced 0, :__id 9, :case 1, :education "6-11yrs", :spontaneous 1}
    {:stratum 10, :parity 2, :age 28, :pooled.stratum 19, :induced 0, :__id 10, :case 1, :education "6-11yrs", :spontaneous 0}
    {:stratum 11, :parity 2, :age 29, :pooled.stratum 20, :induced 1, :__id 11, :case 1, :education "6-11yrs", :spontaneous 0}
    {:stratum 12, :parity 4, :age 37, :pooled.stratum 37, :induced 2, :__id 12, :case 1, :education "6-11yrs", :spontaneous 1}
    {:stratum 13, :parity 1, :age 31, :pooled.stratum 9, :induced 1, :__id 13, :case 1, :education "6-11yrs", :spontaneous 0}
    {:stratum 14, :parity 3, :age 29, :pooled.stratum 29, :induced 2, :__id 14, :case 1, :education "6-11yrs", :spontaneous 0}
    {:stratum 15, :parity 2, :age 31, :pooled.stratum 21, :induced 1, :__id 15, :case 1, :education "6-11yrs", :spontaneous 1}
    {:stratum 16, :parity 2, :age 27, :pooled.stratum 18, :induced 2, :__id 16, :case 1, :education "6-11yrs", :spontaneous 0}
    {:stratum 17, :parity 5, :age 30, :pooled.stratum 38, :induced 2, :__id 17, :case 1, :education "6-11yrs", :spontaneous 1}
    {:stratum 18, :parity 1, :age 26, :pooled.stratum 7, :induced 0, :__id 18, :case 1, :education "6-11yrs", :spontaneous 1}
    {:stratum 19, :parity 3, :age 25, :pooled.stratum 28, :induced 2, :__id 19, :case 1, :education "6-11yrs", :spontaneous 1}
    {:stratum 20, :parity 1, :age 44, :pooled.stratum 17, :induced 0, :__id 20, :case 1, :education "6-11yrs", :spontaneous 1}
    {:stratum 21, :parity 1, :age 40, :pooled.stratum 14, :induced 0, :__id 21, :case 1, :education "6-11yrs", :spontaneous 1}
    {:stratum 22, :parity 2, :age 35, :pooled.stratum 24, :induced 2, :__id 22, :case 1, :education "6-11yrs", :spontaneous 0}
    {:stratum 23, :parity 2, :age 28, :pooled.stratum 19, :induced 0, :__id 23, :case 1, :education "6-11yrs", :spontaneous 2}
    {:stratum 24, :parity 1, :age 36, :pooled.stratum 12, :induced 0, :__id 24, :case 1, :education "6-11yrs", :spontaneous 1}
    {:stratum 25, :parity 2, :age 27, :pooled.stratum 18, :induced 1, :__id 25, :case 1, :education "6-11yrs", :spontaneous 1}
    {:stratum 26, :parity 2, :age 40, :pooled.stratum 27, :induced 0, :__id 26, :case 1, :education "6-11yrs", :spontaneous 2}
    {:stratum 27, :parity 2, :age 38, :pooled.stratum 26, :induced 0, :__id 27, :case 1, :education "6-11yrs", :spontaneous 2}
    {:stratum 28, :parity 3, :age 34, :pooled.stratum 31, :induced 0, :__id 28, :case 1, :education "6-11yrs", :spontaneous 2}
    {:stratum 29, :parity 4, :age 28, :pooled.stratum 34, :induced 1, :__id 29, :case 1, :education "6-11yrs", :spontaneous 2}
    {:stratum 30, :parity 4, :age 30, :pooled.stratum 35, :induced 2, :__id 30, :case 1, :education "6-11yrs", :spontaneous 0}
    {:stratum 31, :parity 1, :age 32, :pooled.stratum 10, :induced 0, :__id 31, :case 1, :education "6-11yrs", :spontaneous 1}
    {:stratum 32, :parity 2, :age 34, :pooled.stratum 23, :induced 1, :__id 32, :case 1, :education "6-11yrs", :spontaneous 0}
    {:stratum 33, :parity 1, :age 42, :pooled.stratum 16, :induced 1, :__id 33, :case 1, :education "6-11yrs", :spontaneous 0}
    {:stratum 34, :parity 2, :age 32, :pooled.stratum 22, :induced 0, :__id 34, :case 1, :education "6-11yrs", :spontaneous 2}
    {:stratum 35, :parity 1, :age 39, :pooled.stratum 13, :induced 1, :__id 35, :case 1, :education "6-11yrs", :spontaneous 0}
    {:stratum 36, :parity 2, :age 35, :pooled.stratum 24, :induced 0, :__id 36, :case 1, :education "6-11yrs", :spontaneous 2}
    {:stratum 37, :parity 1, :age 36, :pooled.stratum 12, :induced 0, :__id 37, :case 1, :education "6-11yrs", :spontaneous 1}
    {:stratum 38, :parity 3, :age 34, :pooled.stratum 31, :induced 1, :__id 38, :case 1, :education "6-11yrs", :spontaneous 2}
    {:stratum 39, :parity 3, :age 30, :pooled.stratum 30, :induced 0, :__id 39, :case 1, :education "6-11yrs", :spontaneous 0}
    {:stratum 40, :parity 1, :age 28, :pooled.stratum 8, :induced 0, :__id 40, :case 1, :education "6-11yrs", :spontaneous 1}
    {:stratum 41, :parity 3, :age 39, :pooled.stratum 33, :induced 0, :__id 41, :case 1, :education "6-11yrs", :spontaneous 2}
    {:stratum 42, :parity 1, :age 35, :pooled.stratum 11, :induced 0, :__id 42, :case 1, :education "6-11yrs", :spontaneous 0}
    {:stratum 43, :parity 1, :age 41, :pooled.stratum 15, :induced 0, :__id 43, :case 1, :education "6-11yrs", :spontaneous 0}
    {:stratum 44, :parity 2, :age 37, :pooled.stratum 25, :induced 1, :__id 44, :case 1, :education "6-11yrs", :spontaneous 1}
    {:stratum 45, :parity 1, :age 30, :pooled.stratum 44, :induced 0, :__id 45, :case 1, :education "12+ yrs", :spontaneous 0}
    {:stratum 46, :parity 1, :age 37, :pooled.stratum 48, :induced 1, :__id 46, :case 1, :education "12+ yrs", :spontaneous 0}
    {:stratum 47, :parity 2, :age 28, :pooled.stratum 51, :induced 0, :__id 47, :case 1, :education "12+ yrs", :spontaneous 2}
    {:stratum 48, :parity 4, :age 27, :pooled.stratum 61, :induced 2, :__id 48, :case 1, :education "12+ yrs", :spontaneous 0}
    {:stratum 49, :parity 2, :age 26, :pooled.stratum 49, :induced 2, :__id 49, :case 1, :education "12+ yrs", :spontaneous 0}
    {:stratum 50, :parity 3, :age 38, :pooled.stratum 60, :induced 0, :__id 50, :case 1, :education "12+ yrs", :spontaneous 2}
    {:stratum 51, :parity 3, :age 24, :pooled.stratum 56, :induced 1, :__id 51, :case 1, :education "12+ yrs", :spontaneous 2}
    {:stratum 52, :parity 5, :age 36, :pooled.stratum 62, :induced 1, :__id 52, :case 1, :education "12+ yrs", :spontaneous 2}
    {:stratum 53, :parity 3, :age 27, :pooled.stratum 57, :induced 1, :__id 53, :case 1, :education "12+ yrs", :spontaneous 1}
    {:stratum 54, :parity 1, :age 28, :pooled.stratum 42, :induced 0, :__id 54, :case 1, :education "12+ yrs", :spontaneous 1}
    {:stratum 55, :parity 2, :age 29, :pooled.stratum 52, :induced 0, :__id 55, :case 1, :education "12+ yrs", :spontaneous 2}
    {:stratum 56, :parity 2, :age 36, :pooled.stratum 55, :induced 0, :__id 56, :case 1, :education "12+ yrs", :spontaneous 2}
    {:stratum 57, :parity 2, :age 28, :pooled.stratum 51, :induced 1, :__id 57, :case 1, :education "12+ yrs", :spontaneous 0}
    {:stratum 58, :parity 2, :age 28, :pooled.stratum 51, :induced 0, :__id 58, :case 1, :education "12+ yrs", :spontaneous 2}
    {:stratum 59, :parity 1, :age 28, :pooled.stratum 42, :induced 0, :__id 59, :case 1, :education "12+ yrs", :spontaneous 1}
    {:stratum 60, :parity 2, :age 27, :pooled.stratum 50, :induced 0, :__id 60, :case 1, :education "12+ yrs", :spontaneous 2}
    {:stratum 61, :parity 2, :age 35, :pooled.stratum 54, :induced 0, :__id 61, :case 1, :education "12+ yrs", :spontaneous 2}
    {:stratum 62, :parity 1, :age 25, :pooled.stratum 41, :induced 0, :__id 62, :case 1, :education "12+ yrs", :spontaneous 1}
    {:stratum 63, :parity 1, :age 34, :pooled.stratum 47, :induced 0, :__id 63, :case 1, :education "12+ yrs", :spontaneous 1}
    {:stratum 64, :parity 2, :age 31, :pooled.stratum 53, :induced 0, :__id 64, :case 1, :education "12+ yrs", :spontaneous 2}
    {:stratum 65, :parity 2, :age 26, :pooled.stratum 49, :induced 1, :__id 65, :case 1, :education "12+ yrs", :spontaneous 0}
    {:stratum 66, :parity 1, :age 32, :pooled.stratum 46, :induced 0, :__id 66, :case 1, :education "12+ yrs", :spontaneous 1}
    {:stratum 67, :parity 1, :age 21, :pooled.stratum 39, :induced 0, :__id 67, :case 1, :education "12+ yrs", :spontaneous 1}
    {:stratum 68, :parity 3, :age 28, :pooled.stratum 58, :induced 1, :__id 68, :case 1, :education "12+ yrs", :spontaneous 2}
    {:stratum 69, :parity 3, :age 37, :pooled.stratum 59, :induced 0, :__id 69, :case 1, :education "12+ yrs", :spontaneous 2}
    {:stratum 70, :parity 1, :age 25, :pooled.stratum 41, :induced 1, :__id 70, :case 1, :education "12+ yrs", :spontaneous 0}
    {:stratum 71, :parity 1, :age 32, :pooled.stratum 46, :induced 1, :__id 71, :case 1, :education "12+ yrs", :spontaneous 0}
    {:stratum 72, :parity 1, :age 25, :pooled.stratum 41, :induced 0, :__id 72, :case 1, :education "12+ yrs", :spontaneous 1}
    {:stratum 73, :parity 1, :age 31, :pooled.stratum 45, :induced 0, :__id 73, :case 1, :education "12+ yrs", :spontaneous 1}
    {:stratum 74, :parity 6, :age 38, :pooled.stratum 63, :induced 0, :__id 74, :case 1, :education "12+ yrs", :spontaneous 2}
    {:stratum 75, :parity 2, :age 26, :pooled.stratum 49, :induced 0, :__id 75, :case 1, :education "12+ yrs", :spontaneous 2}
    {:stratum 76, :parity 1, :age 31, :pooled.stratum 45, :induced 0, :__id 76, :case 1, :education "12+ yrs", :spontaneous 1}
    {:stratum 77, :parity 2, :age 31, :pooled.stratum 53, :induced 0, :__id 77, :case 1, :education "12+ yrs", :spontaneous 1}
    {:stratum 78, :parity 1, :age 25, :pooled.stratum 41, :induced 1, :__id 78, :case 1, :education "12+ yrs", :spontaneous 0}
    {:stratum 79, :parity 1, :age 31, :pooled.stratum 45, :induced 0, :__id 79, :case 1, :education "12+ yrs", :spontaneous 1}
    {:stratum 80, :parity 1, :age 34, :pooled.stratum 47, :induced 0, :__id 80, :case 1, :education "12+ yrs", :spontaneous 1}
    {:stratum 81, :parity 2, :age 35, :pooled.stratum 54, :induced 2, :__id 81, :case 1, :education "12+ yrs", :spontaneous 0}
    {:stratum 82, :parity 1, :age 29, :pooled.stratum 43, :induced 0, :__id 82, :case 1, :education "12+ yrs", :spontaneous 1}
    {:stratum 83, :parity 1, :age 23, :pooled.stratum 40, :induced 0, :__id 83, :case 1, :education "12+ yrs", :spontaneous 1}
    {:stratum 1, :parity 6, :age 26, :pooled.stratum 3, :induced 2, :__id 84, :case 0, :education "0-5yrs", :spontaneous 0}
    {:stratum 2, :parity 1, :age 42, :pooled.stratum 1, :induced 0, :__id 85, :case 0, :education "0-5yrs", :spontaneous 0} {:stratum 3, :parity 6, :age 39, :pooled.stratum 4, :induced 2, :__id 86, :case 0, :education "0-5yrs", :spontaneous 0}
    {:stratum 4, :parity 4, :age 34, :pooled.stratum 2, :induced 0, :__id 87, :case 0, :education "0-5yrs", :spontaneous 1}
    {:stratum 5, :parity 3, :age 35, :pooled.stratum 32, :induced 2, :__id 88, :case 0, :education "6-11yrs", :spontaneous 0}
    {:stratum 6, :parity 4, :age 36, :pooled.stratum 36, :induced 1, :__id 89, :case 0, :education "6-11yrs", :spontaneous 1}
    {:stratum 7, :parity 1, :age 23, :pooled.stratum 6, :induced 0, :__id 90, :case 0, :education "6-11yrs", :spontaneous 0}
    {:stratum 8, :parity 2, :age 32, :pooled.stratum 22, :induced 2, :__id 91, :case 0, :education "6-11yrs", :spontaneous 0}
    {:stratum 9, :parity 1, :age 21, :pooled.stratum 5, :induced 0, :__id 92, :case 0, :education "6-11yrs", :spontaneous 1}
    {:stratum 10, :parity 2, :age 28, :pooled.stratum 19, :induced 0, :__id 93, :case 0, :education "6-11yrs", :spontaneous 1}
    {:stratum 11, :parity 2, :age 29, :pooled.stratum 20, :induced 0, :__id 94, :case 0, :education "6-11yrs", :spontaneous 0}
    {:stratum 12, :parity 4, :age 37, :pooled.stratum 37, :induced 1, :__id 95, :case 0, :education "6-11yrs", :spontaneous 1}
    {:stratum 13, :parity 1, :age 31, :pooled.stratum 9, :induced 0, :__id 96, :case 0, :education "6-11yrs", :spontaneous 0}
    {:stratum 14, :parity 3, :age 29, :pooled.stratum 29, :induced 0, :__id 97, :case 0, :education "6-11yrs", :spontaneous 1}
    {:stratum 15, :parity 2, :age 31, :pooled.stratum 21, :induced 1, :__id 98, :case 0, :education "6-11yrs", :spontaneous 0}
    {:stratum 16, :parity 2, :age 27, :pooled.stratum 18, :induced 1, :__id 99, :case 0, :education "6-11yrs", :spontaneous 0}
    {:stratum 17, :parity 5, :age 30, :pooled.stratum 38, :induced 0, :__id 100, :case 0, :education "6-11yrs", :spontaneous 2}
    {:stratum 18, :parity 1, :age 26, :pooled.stratum 7, :induced 0, :__id 101, :case 0, :education "6-11yrs", :spontaneous 0}
    {:stratum 19, :parity 3, :age 25, :pooled.stratum 28, :induced 0, :__id 102, :case 0, :education "6-11yrs", :spontaneous 1}
    {:stratum 20, :parity 1, :age 44, :pooled.stratum 17, :induced 0, :__id 103, :case 0, :education "6-11yrs", :spontaneous 0}
    {:stratum 21, :parity 1, :age 40, :pooled.stratum 14, :induced 0, :__id 104, :case 0, :education "6-11yrs", :spontaneous 0}
    {:stratum 22, :parity 2, :age 35, :pooled.stratum 24, :induced 0, :__id 105, :case 0, :education "6-11yrs", :spontaneous 0}
    {:stratum 23, :parity 2, :age 28, :pooled.stratum 19, :induced 0, :__id 106, :case 0, :education "6-11yrs", :spontaneous 0}
    {:stratum 24, :parity 1, :age 36, :pooled.stratum 12, :induced 0, :__id 107, :case 0, :education "6-11yrs", :spontaneous 0}
    {:stratum 25, :parity 2, :age 27, :pooled.stratum 18, :induced 0, :__id 108, :case 0, :education "6-11yrs", :spontaneous 1}
    {:stratum 26, :parity 2, :age 40, :pooled.stratum 27, :induced 0, :__id 109, :case 0, :education "6-11yrs", :spontaneous 0}
    {:stratum 27, :parity 2, :age 38, :pooled.stratum 26, :induced 0, :__id 110, :case 0, :education "6-11yrs", :spontaneous 0}
    {:stratum 28, :parity 3, :age 34, :pooled.stratum 31, :induced 0, :__id 111, :case 0, :education "6-11yrs", :spontaneous 0}
    {:stratum 29, :parity 4, :age 28, :pooled.stratum 34, :induced 0, :__id 112, :case 0, :education "6-11yrs", :spontaneous 2}
    {:stratum 30, :parity 4, :age 30, :pooled.stratum 35, :induced 1, :__id 113, :case 0, :education "6-11yrs", :spontaneous 1}
    {:stratum 31, :parity 1, :age 32, :pooled.stratum 10, :induced 0, :__id 114, :case 0, :education "6-11yrs", :spontaneous 0}
    {:stratum 32, :parity 2, :age 34, :pooled.stratum 23, :induced 1, :__id 115, :case 0, :education "6-11yrs", :spontaneous 0}
    {:stratum 33, :parity 1, :age 42, :pooled.stratum 16, :induced 1, :__id 116, :case 0, :education "6-11yrs", :spontaneous 0}
    {:stratum 34, :parity 2, :age 32, :pooled.stratum 22, :induced 0, :__id 117, :case 0, :education "6-11yrs", :spontaneous 0}
    {:stratum 35, :parity 1, :age 39, :pooled.stratum 13, :induced 0, :__id 118, :case 0, :education "6-11yrs", :spontaneous 0}
    {:stratum 36, :parity 2, :age 35, :pooled.stratum 24, :induced 0, :__id 119, :case 0, :education "6-11yrs", :spontaneous 0}
    {:stratum 37, :parity 1, :age 36, :pooled.stratum 12, :induced 0, :__id 120, :case 0, :education "6-11yrs", :spontaneous 0}
    {:stratum 38, :parity 3, :age 34, :pooled.stratum 31, :induced 2, :__id 121, :case 0, :education "6-11yrs", :spontaneous 0}
    {:stratum 39, :parity 3, :age 30, :pooled.stratum 30, :induced 0, :__id 122, :case 0, :education "6-11yrs", :spontaneous 2}
    {:stratum 40, :parity 1, :age 28, :pooled.stratum 8, :induced 1, :__id 123, :case 0, :education "6-11yrs", :spontaneous 0}
    {:stratum 41, :parity 3, :age 39, :pooled.stratum 33, :induced 1, :__id 124, :case 0, :education "6-11yrs", :spontaneous 0}
    {:stratum 42, :parity 1, :age 35, :pooled.stratum 11, :induced 0, :__id 125, :case 0, :education "6-11yrs", :spontaneous 0}
    {:stratum 43, :parity 1, :age 41, :pooled.stratum 15, :induced 0, :__id 126, :case 0, :education "6-11yrs", :spontaneous 0}
    {:stratum 44, :parity 2, :age 37, :pooled.stratum 25, :induced 0, :__id 127, :case 0, :education "6-11yrs", :spontaneous 0}
    {:stratum 45, :parity 1, :age 30, :pooled.stratum 44, :induced 1, :__id 128, :case 0, :education "12+ yrs", :spontaneous 0}
    {:stratum 46, :parity 1, :age 37, :pooled.stratum 48, :induced 0, :__id 129, :case 0, :education "12+ yrs", :spontaneous 0}
    {:stratum 47, :parity 2, :age 28, :pooled.stratum 51, :induced 1, :__id 130, :case 0, :education "12+ yrs", :spontaneous 0}
    {:stratum 48, :parity 4, :age 27, :pooled.stratum 61, :induced 2, :__id 131, :case 0, :education "12+ yrs", :spontaneous 1}
    {:stratum 49, :parity 2, :age 26, :pooled.stratum 49, :induced 1, :__id 132, :case 0, :education "12+ yrs", :spontaneous 0}
    {:stratum 50, :parity 3, :age 38, :pooled.stratum 60, :induced 1, :__id 133, :case 0, :education "12+ yrs", :spontaneous 0}
    {:stratum 51, :parity 3, :age 24, :pooled.stratum 56, :induced 2, :__id 134, :case 0, :education "12+ yrs", :spontaneous 1}
    {:stratum 52, :parity 5, :age 36, :pooled.stratum 62, :induced 1, :__id 135, :case 0, :education "12+ yrs", :spontaneous 1}
    {:stratum 53, :parity 3, :age 27, :pooled.stratum 57, :induced 1, :__id 136, :case 0, :education "12+ yrs", :spontaneous 1}
    {:stratum 54, :parity 1, :age 28, :pooled.stratum 42, :induced 1, :__id 137, :case 0, :education "12+ yrs", :spontaneous 0}
    {:stratum 55, :parity 2, :age 29, :pooled.stratum 52, :induced 1, :__id 138, :case 0, :education "12+ yrs", :spontaneous 0}
    {:stratum 56, :parity 2, :age 36, :pooled.stratum 55, :induced 1, :__id 139, :case 0, :education "12+ yrs", :spontaneous 0}
    {:stratum 57, :parity 2, :age 28, :pooled.stratum 51, :induced 1, :__id 140, :case 0, :education "12+ yrs", :spontaneous 1}
    {:stratum 58, :parity 2, :age 28, :pooled.stratum 51, :induced 2, :__id 141, :case 0, :education "12+ yrs", :spontaneous 0}
    {:stratum 59, :parity 1, :age 28, :pooled.stratum 42, :induced 1, :__id 142, :case 0, :education "12+ yrs", :spontaneous 0}
    {:stratum 60, :parity 2, :age 27, :pooled.stratum 50, :induced 1, :__id 143, :case 0, :education "12+ yrs", :spontaneous 0}
    {:stratum 61, :parity 2, :age 35, :pooled.stratum 54, :induced 2, :__id 144, :case 0, :education "12+ yrs", :spontaneous 0}
    {:stratum 62, :parity 1, :age 25, :pooled.stratum 41, :induced 1, :__id 145, :case 0, :education "12+ yrs", :spontaneous 0}
    {:stratum 63, :parity 1, :age 34, :pooled.stratum 47, :induced 0, :__id 146, :case 0, :education "12+ yrs", :spontaneous 0}
    {:stratum 64, :parity 2, :age 31, :pooled.stratum 53, :induced 0, :__id 147, :case 0, :education "12+ yrs", :spontaneous 0}
    {:stratum 65, :parity 2, :age 26, :pooled.stratum 49, :induced 0, :__id 148, :case 0, :education "12+ yrs", :spontaneous 1}
    {:stratum 66, :parity 1, :age 32, :pooled.stratum 46, :induced 0, :__id 149, :case 0, :education "12+ yrs", :spontaneous 0}
    {:stratum 67, :parity 1, :age 21, :pooled.stratum 39, :induced 0, :__id 150, :case 0, :education "12+ yrs", :spontaneous 1}
    {:stratum 68, :parity 3, :age 28, :pooled.stratum 58, :induced 2, :__id 151, :case 0, :education "12+ yrs", :spontaneous 0}
    {:stratum 69, :parity 3, :age 37, :pooled.stratum 59, :induced 1, :__id 152, :case 0, :education "12+ yrs", :spontaneous 1}
    {:stratum 70, :parity 1, :age 25, :pooled.stratum 41, :induced 0, :__id 153, :case 0, :education "12+ yrs", :spontaneous 0}
    {:stratum 71, :parity 1, :age 32, :pooled.stratum 46, :induced 1, :__id 154, :case 0, :education "12+ yrs", :spontaneous 0}
    {:stratum 72, :parity 1, :age 25, :pooled.stratum 41, :induced 0, :__id 155, :case 0, :education "12+ yrs", :spontaneous 0}
    {:stratum 73, :parity 1, :age 31, :pooled.stratum 45, :induced 0, :__id 156, :case 0, :education "12+ yrs", :spontaneous 1}
    {:stratum 75, :parity 2, :age 26, :pooled.stratum 49, :induced 0, :__id 157, :case 0, :education "12+ yrs", :spontaneous 2}
    {:stratum 76, :parity 1, :age 31, :pooled.stratum 45, :induced 0, :__id 158, :case 0, :education "12+ yrs", :spontaneous 0}
    {:stratum 77, :parity 2, :age 31, :pooled.stratum 53, :induced 2, :__id 159, :case 0, :education "12+ yrs", :spontaneous 0}
    {:stratum 78, :parity 1, :age 25, :pooled.stratum 41, :induced 0, :__id 160, :case 0, :education "12+ yrs", :spontaneous 0}
    {:stratum 79, :parity 1, :age 31, :pooled.stratum 45, :induced 0, :__id 161, :case 0, :education "12+ yrs", :spontaneous 0}
    {:stratum 80, :parity 1, :age 34, :pooled.stratum 47, :induced 0, :__id 162, :case 0, :education "12+ yrs", :spontaneous 0}
    {:stratum 81, :parity 2, :age 35, :pooled.stratum 54, :induced 0, :__id 163, :case 0, :education "12+ yrs", :spontaneous 0}
    {:stratum 82, :parity 1, :age 29, :pooled.stratum 43, :induced 0, :__id 164, :case 0, :education "12+ yrs", :spontaneous 1}
    {:stratum 83, :parity 1, :age 23, :pooled.stratum 40, :induced 0, :__id 165, :case 0, :education "12+ yrs", :spontaneous 1}
    {:stratum 1, :parity 6, :age 26, :pooled.stratum 3, :induced 2, :__id 166, :case 0, :education "0-5yrs", :spontaneous 0}
    {:stratum 2, :parity 1, :age 42, :pooled.stratum 1, :induced 0, :__id 167, :case 0, :education "0-5yrs", :spontaneous 0}
    {:stratum 3, :parity 6, :age 39, :pooled.stratum 4, :induced 2, :__id 168, :case 0, :education "0-5yrs", :spontaneous 0}
    {:stratum 4, :parity 4, :age 34, :pooled.stratum 2, :induced 0, :__id 169, :case 0, :education "0-5yrs", :spontaneous 2}
    {:stratum 5, :parity 3, :age 35, :pooled.stratum 32, :induced 0, :__id 170, :case 0, :education "6-11yrs", :spontaneous 0}
    {:stratum 6, :parity 4, :age 36, :pooled.stratum 36, :induced 0, :__id 171, :case 0, :education "6-11yrs", :spontaneous 2}
    {:stratum 7, :parity 1, :age 23, :pooled.stratum 6, :induced 0, :__id 172, :case 0, :education "6-11yrs", :spontaneous 0}
    {:stratum 8, :parity 2, :age 32, :pooled.stratum 22, :induced 0, :__id 173, :case 0, :education "6-11yrs", :spontaneous 1}
    {:stratum 9, :parity 1, :age 21, :pooled.stratum 5, :induced 1, :__id 174, :case 0, :education "6-11yrs", :spontaneous 0}
    {:stratum 10, :parity 2, :age 28, :pooled.stratum 19, :induced 0, :__id 175, :case 0, :education "6-11yrs", :spontaneous 1}
    {:stratum 11, :parity 2, :age 29, :pooled.stratum 20, :induced 0, :__id 176, :case 0, :education "6-11yrs", :spontaneous 1}
    {:stratum 12, :parity 4, :age 37, :pooled.stratum 37, :induced 0, :__id 177, :case 0, :education "6-11yrs", :spontaneous 1}
    {:stratum 13, :parity 1, :age 31, :pooled.stratum 9, :induced 0, :__id 178, :case 0, :education "6-11yrs", :spontaneous 0}
    {:stratum 14, :parity 3, :age 29, :pooled.stratum 29, :induced 0, :__id 179, :case 0, :education "6-11yrs", :spontaneous 2}
    {:stratum 15, :parity 2, :age 31, :pooled.stratum 21, :induced 1, :__id 180, :case 0, :education "6-11yrs", :spontaneous 0}
    {:stratum 16, :parity 2, :age 27, :pooled.stratum 18, :induced 0, :__id 181, :case 0, :education "6-11yrs", :spontaneous 0}
    {:stratum 17, :parity 5, :age 30, :pooled.stratum 38, :induced 1, :__id 182, :case 0, :education "6-11yrs", :spontaneous 2}
    {:stratum 18, :parity 1, :age 26, :pooled.stratum 7, :induced 1, :__id 183, :case 0, :education "6-11yrs", :spontaneous 0}
    {:stratum 19, :parity 3, :age 25, :pooled.stratum 28, :induced 1, :__id 184, :case 0, :education "6-11yrs", :spontaneous 1}
    {:stratum 20, :parity 1, :age 44, :pooled.stratum 17, :induced 1, :__id 185, :case 0, :education "6-11yrs", :spontaneous 0}
    {:stratum 21, :parity 1, :age 40, :pooled.stratum 14, :induced 0, :__id 186, :case 0, :education "6-11yrs", :spontaneous 0}
    {:stratum 22, :parity 2, :age 35, :pooled.stratum 24, :induced 0, :__id 187, :case 0, :education "6-11yrs", :spontaneous 0}
    {:stratum 23, :parity 2, :age 28, :pooled.stratum 19, :induced 2, :__id 188, :case 0, :education "6-11yrs", :spontaneous 0}
    {:stratum 24, :parity 1, :age 36, :pooled.stratum 12, :induced 0, :__id 189, :case 0, :education "6-11yrs", :spontaneous 1}
    {:stratum 25, :parity 2, :age 27, :pooled.stratum 18, :induced 0, :__id 190, :case 0, :education "6-11yrs", :spontaneous 2}
    {:stratum 26, :parity 2, :age 40, :pooled.stratum 27, :induced 0, :__id 191, :case 0, :education "6-11yrs", :spontaneous 0}
    {:stratum 27, :parity 2, :age 38, :pooled.stratum 26, :induced 0, :__id 192, :case 0, :education "6-11yrs", :spontaneous 0}
    {:stratum 28, :parity 3, :age 34, :pooled.stratum 31, :induced 0, :__id 193, :case 0, :education "6-11yrs", :spontaneous 0}
    {:stratum 29, :parity 4, :age 28, :pooled.stratum 34, :induced 2, :__id 194, :case 0, :education "6-11yrs", :spontaneous 1}
    {:stratum 30, :parity 4, :age 30, :pooled.stratum 35, :induced 1, :__id 195, :case 0, :education "6-11yrs", :spontaneous 1}
    {:stratum 31, :parity 1, :age 32, :pooled.stratum 10, :induced 0, :__id 196, :case 0, :education "6-11yrs", :spontaneous 0}
    {:stratum 32, :parity 2, :age 34, :pooled.stratum 23, :induced 0, :__id 197, :case 0, :education "6-11yrs", :spontaneous 0}
    {:stratum 33, :parity 1, :age 42, :pooled.stratum 16, :induced 0, :__id 198, :case 0, :education "6-11yrs", :spontaneous 0}
    {:stratum 34, :parity 2, :age 32, :pooled.stratum 22, :induced 2, :__id 199, :case 0, :education "6-11yrs", :spontaneous 0}
    {:stratum 35, :parity 1, :age 39, :pooled.stratum 13, :induced 0, :__id 200, :case 0, :education "6-11yrs", :spontaneous 0}
    {:stratum 36, :parity 2, :age 35, :pooled.stratum 24, :induced 0, :__id 201, :case 0, :education "6-11yrs", :spontaneous 0}
    {:stratum 37, :parity 1, :age 36, :pooled.stratum 12, :induced 0, :__id 202, :case 0, :education "6-11yrs", :spontaneous 0}
    {:stratum 38, :parity 3, :age 34, :pooled.stratum 31, :induced 2, :__id 203, :case 0, :education "6-11yrs", :spontaneous 0}
    {:stratum 39, :parity 3, :age 30, :pooled.stratum 30, :induced 0, :__id 204, :case 0, :education "6-11yrs", :spontaneous 1}
    {:stratum 40, :parity 1, :age 28, :pooled.stratum 8, :induced 0, :__id 205, :case 0, :education "6-11yrs", :spontaneous 0}
    {:stratum 41, :parity 3, :age 39, :pooled.stratum 33, :induced 0, :__id 206, :case 0, :education "6-11yrs", :spontaneous 0}
    {:stratum 42, :parity 1, :age 35, :pooled.stratum 11, :induced 0, :__id 207, :case 0, :education "6-11yrs", :spontaneous 0}
    {:stratum 43, :parity 1, :age 41, :pooled.stratum 15, :induced 0, :__id 208, :case 0, :education "6-11yrs", :spontaneous 0}
    {:stratum 44, :parity 2, :age 37, :pooled.stratum 25, :induced 0, :__id 209, :case 0, :education "6-11yrs", :spontaneous 0}
    {:stratum 45, :parity 1, :age 30, :pooled.stratum 44, :induced 0, :__id 210, :case 0, :education "12+ yrs", :spontaneous 0}
    {:stratum 46, :parity 1, :age 37, :pooled.stratum 48, :induced 0, :__id 211, :case 0, :education "12+ yrs", :spontaneous 1}
    {:stratum 47, :parity 2, :age 28, :pooled.stratum 51, :induced 1, :__id 212, :case 0, :education "12+ yrs", :spontaneous 0}
    {:stratum 48, :parity 4, :age 27, :pooled.stratum 61, :induced 2, :__id 213, :case 0, :education "12+ yrs", :spontaneous 0}
    {:stratum 49, :parity 2, :age 26, :pooled.stratum 49, :induced 1, :__id 214, :case 0, :education "12+ yrs", :spontaneous 0}
    {:stratum 50, :parity 3, :age 38, :pooled.stratum 60, :induced 1, :__id 215, :case 0, :education "12+ yrs", :spontaneous 0}
    {:stratum 51, :parity 3, :age 24, :pooled.stratum 56, :induced 2, :__id 216, :case 0, :education "12+ yrs", :spontaneous 0}
    {:stratum 52, :parity 5, :age 36, :pooled.stratum 62, :induced 2, :__id 217, :case 0, :education "12+ yrs", :spontaneous 1}
    {:stratum 53, :parity 3, :age 27, :pooled.stratum 57, :induced 2, :__id 218, :case 0, :education "12+ yrs", :spontaneous 0}
    {:stratum 54, :parity 1, :age 28, :pooled.stratum 42, :induced 0, :__id 219, :case 0, :education "12+ yrs", :spontaneous 1}
    {:stratum 55, :parity 2, :age 29, :pooled.stratum 52, :induced 1, :__id 220, :case 0, :education "12+ yrs", :spontaneous 1}
    {:stratum 56, :parity 2, :age 36, :pooled.stratum 55, :induced 0, :__id 221, :case 0, :education "12+ yrs", :spontaneous 1}
    {:stratum 57, :parity 2, :age 28, :pooled.stratum 51, :induced 2, :__id 222, :case 0, :education "12+ yrs", :spontaneous 0}
    {:stratum 58, :parity 2, :age 28, :pooled.stratum 51, :induced 1, :__id 223, :case 0, :education "12+ yrs", :spontaneous 0}
    {:stratum 59, :parity 1, :age 28, :pooled.stratum 42, :induced 0, :__id 224, :case 0, :education "12+ yrs", :spontaneous 0}
    {:stratum 60, :parity 2, :age 27, :pooled.stratum 50, :induced 1, :__id 225, :case 0, :education "12+ yrs", :spontaneous 0}
    {:stratum 61, :parity 2, :age 35, :pooled.stratum 54, :induced 1, :__id 226, :case 0, :education "12+ yrs", :spontaneous 0}
    {:stratum 62, :parity 1, :age 25, :pooled.stratum 41, :induced 1, :__id 227, :case 0, :education "12+ yrs", :spontaneous 0}
    {:stratum 63, :parity 1, :age 34, :pooled.stratum 47, :induced 0, :__id 228, :case 0, :education "12+ yrs", :spontaneous 0}
    {:stratum 64, :parity 2, :age 31, :pooled.stratum 53, :induced 1, :__id 229, :case 0, :education "12+ yrs", :spontaneous 0}
    {:stratum 65, :parity 2, :age 26, :pooled.stratum 49, :induced 0, :__id 230, :case 0, :education "12+ yrs", :spontaneous 2}
    {:stratum 66, :parity 1, :age 32, :pooled.stratum 46, :induced 1, :__id 231, :case 0, :education "12+ yrs", :spontaneous 0}
    {:stratum 67, :parity 1, :age 21, :pooled.stratum 39, :induced 0, :__id 232, :case 0, :education "12+ yrs", :spontaneous 0}
    {:stratum 68, :parity 3, :age 28, :pooled.stratum 58, :induced 2, :__id 233, :case 0, :education "12+ yrs", :spontaneous 0}
    {:stratum 69, :parity 3, :age 37, :pooled.stratum 59, :induced 0, :__id 234, :case 0, :education "12+ yrs", :spontaneous 2}
    {:stratum 70, :parity 1, :age 25, :pooled.stratum 41, :induced 1, :__id 235, :case 0, :education "12+ yrs", :spontaneous 0}
    {:stratum 71, :parity 1, :age 32, :pooled.stratum 46, :induced 0, :__id 236, :case 0, :education "12+ yrs", :spontaneous 0}
    {:stratum 72, :parity 1, :age 25, :pooled.stratum 41, :induced 1, :__id 237, :case 0, :education "12+ yrs", :spontaneous 0}
    {:stratum 73, :parity 1, :age 31, :pooled.stratum 45, :induced 0, :__id 238, :case 0, :education "12+ yrs", :spontaneous 0}
    {:stratum 74, :parity 6, :age 38, :pooled.stratum 63, :induced 0, :__id 239, :case 0, :education "12+ yrs", :spontaneous 2}
    {:stratum 75, :parity 2, :age 26, :pooled.stratum 49, :induced 1, :__id 240, :case 0, :education "12+ yrs", :spontaneous 1}
    {:stratum 76, :parity 1, :age 31, :pooled.stratum 45, :induced 1, :__id 241, :case 0, :education "12+ yrs", :spontaneous 0}
    {:stratum 77, :parity 2, :age 31, :pooled.stratum 53, :induced 0, :__id 242, :case 0, :education "12+ yrs", :spontaneous 1}
    {:stratum 78, :parity 1, :age 25, :pooled.stratum 41, :induced 0, :__id 243, :case 0, :education "12+ yrs", :spontaneous 1}
    {:stratum 79, :parity 1, :age 31, :pooled.stratum 45, :induced 0, :__id 244, :case 0, :education "12+ yrs", :spontaneous 1}
    {:stratum 80, :parity 1, :age 34, :pooled.stratum 47, :induced 0, :__id 245, :case 0, :education "12+ yrs", :spontaneous 0}
    {:stratum 81, :parity 2, :age 35, :pooled.stratum 54, :induced 2, :__id 246, :case 0, :education "12+ yrs", :spontaneous 0}
    {:stratum 82, :parity 1, :age 29, :pooled.stratum 43, :induced 0, :__id 247, :case 0, :education "12+ yrs", :spontaneous 1}
    {:stratum 83, :parity 1, :age 23, :pooled.stratum 40, :induced 0, :__id 248, :case 0, :education "12+ yrs", :spontaneous 1})
   "iris"
   ({:sepal_length 5.1, :sepal_width 3.5, :petal_length 1.4, :petal_width 0.2, :variety "Setosa"}
    {:sepal_length 4.9, :sepal_width 3, :petal_length 1.4, :petal_width 0.2, :variety "Setosa"}
    {:sepal_length 4.7, :sepal_width 3.2, :petal_length 1.3, :petal_width 0.2, :variety "Setosa"}
    {:sepal_length 4.6, :sepal_width 3.1, :petal_length 1.5, :petal_width 0.2, :variety "Setosa"}
    {:sepal_length 5, :sepal_width 3.6, :petal_length 1.4, :petal_width 0.2, :variety "Setosa"}
    {:sepal_length 5.4, :sepal_width 3.9, :petal_length 1.7, :petal_width 0.4, :variety "Setosa"}
    {:sepal_length 4.6, :sepal_width 3.4, :petal_length 1.4, :petal_width 0.3, :variety "Setosa"}
    {:sepal_length 5, :sepal_width 3.4, :petal_length 1.5, :petal_width 0.2, :variety "Setosa"}
    {:sepal_length 4.4, :sepal_width 2.9, :petal_length 1.4, :petal_width 0.2, :variety "Setosa"}
    {:sepal_length 4.9, :sepal_width 3.1, :petal_length 1.5, :petal_width 0.1, :variety "Setosa"}
    {:sepal_length 5.4, :sepal_width 3.7, :petal_length 1.5, :petal_width 0.2, :variety "Setosa"}
    {:sepal_length 4.8, :sepal_width 3.4, :petal_length 1.6, :petal_width 0.2, :variety "Setosa"}
    {:sepal_length 4.8, :sepal_width 3, :petal_length 1.4, :petal_width 0.1, :variety "Setosa"}
    {:sepal_length 4.3, :sepal_width 3, :petal_length 1.1, :petal_width 0.1, :variety "Setosa"}
    {:sepal_length 5.8, :sepal_width 4, :petal_length 1.2, :petal_width 0.2, :variety "Setosa"}
    {:sepal_length 5.7, :sepal_width 4.4, :petal_length 1.5, :petal_width 0.4, :variety "Setosa"}
    {:sepal_length 5.4, :sepal_width 3.9, :petal_length 1.3, :petal_width 0.4, :variety "Setosa"}
    {:sepal_length 5.1, :sepal_width 3.5, :petal_length 1.4, :petal_width 0.3, :variety "Setosa"}
    {:sepal_length 5.7, :sepal_width 3.8, :petal_length 1.7, :petal_width 0.3, :variety "Setosa"}
    {:sepal_length 5.1, :sepal_width 3.8, :petal_length 1.5, :petal_width 0.3, :variety "Setosa"}
    {:sepal_length 5.4, :sepal_width 3.4, :petal_length 1.7, :petal_width 0.2, :variety "Setosa"}
    {:sepal_length 5.1, :sepal_width 3.7, :petal_length 1.5, :petal_width 0.4, :variety "Setosa"}
    {:sepal_length 4.6, :sepal_width 3.6, :petal_length 1, :petal_width 0.2, :variety "Setosa"}
    {:sepal_length 5.1, :sepal_width 3.3, :petal_length 1.7, :petal_width 0.5, :variety "Setosa"}
    {:sepal_length 4.8, :sepal_width 3.4, :petal_length 1.9, :petal_width 0.2, :variety "Setosa"}
    {:sepal_length 5, :sepal_width 3, :petal_length 1.6, :petal_width 0.2, :variety "Setosa"}
    {:sepal_length 5, :sepal_width 3.4, :petal_length 1.6, :petal_width 0.4, :variety "Setosa"}
    {:sepal_length 5.2, :sepal_width 3.5, :petal_length 1.5, :petal_width 0.2, :variety "Setosa"}
    {:sepal_length 5.2, :sepal_width 3.4, :petal_length 1.4, :petal_width 0.2, :variety "Setosa"}
    {:sepal_length 4.7, :sepal_width 3.2, :petal_length 1.6, :petal_width 0.2, :variety "Setosa"}
    {:sepal_length 4.8, :sepal_width 3.1, :petal_length 1.6, :petal_width 0.2, :variety "Setosa"}
    {:sepal_length 5.4, :sepal_width 3.4, :petal_length 1.5, :petal_width 0.4, :variety "Setosa"}
    {:sepal_length 5.2, :sepal_width 4.1, :petal_length 1.5, :petal_width 0.1, :variety "Setosa"}
    {:sepal_length 5.5, :sepal_width 4.2, :petal_length 1.4, :petal_width 0.2, :variety "Setosa"}
    {:sepal_length 4.9, :sepal_width 3.1, :petal_length 1.5, :petal_width 0.2, :variety "Setosa"}
    {:sepal_length 5, :sepal_width 3.2, :petal_length 1.2, :petal_width 0.2, :variety "Setosa"}
    {:sepal_length 5.5, :sepal_width 3.5, :petal_length 1.3, :petal_width 0.2, :variety "Setosa"}
    {:sepal_length 4.9, :sepal_width 3.6, :petal_length 1.4, :petal_width 0.1, :variety "Setosa"}
    {:sepal_length 4.4, :sepal_width 3, :petal_length 1.3, :petal_width 0.2, :variety "Setosa"}
    {:sepal_length 5.1, :sepal_width 3.4, :petal_length 1.5, :petal_width 0.2, :variety "Setosa"}
    {:sepal_length 5, :sepal_width 3.5, :petal_length 1.3, :petal_width 0.3, :variety "Setosa"}
    {:sepal_length 4.5, :sepal_width 2.3, :petal_length 1.3, :petal_width 0.3, :variety "Setosa"}
    {:sepal_length 4.4, :sepal_width 3.2, :petal_length 1.3, :petal_width 0.2, :variety "Setosa"}
    {:sepal_length 5, :sepal_width 3.5, :petal_length 1.6, :petal_width 0.6, :variety "Setosa"}
    {:sepal_length 5.1, :sepal_width 3.8, :petal_length 1.9, :petal_width 0.4, :variety "Setosa"}
    {:sepal_length 4.8, :sepal_width 3, :petal_length 1.4, :petal_width 0.3, :variety "Setosa"}
    {:sepal_length 5.1, :sepal_width 3.8, :petal_length 1.6, :petal_width 0.2, :variety "Setosa"}
    {:sepal_length 4.6, :sepal_width 3.2, :petal_length 1.4, :petal_width 0.2, :variety "Setosa"}
    {:sepal_length 5.3, :sepal_width 3.7, :petal_length 1.5, :petal_width 0.2, :variety "Setosa"}
    {:sepal_length 5, :sepal_width 3.3, :petal_length 1.4, :petal_width 0.2, :variety "Setosa"}
    {:sepal_length 7, :sepal_width 3.2, :petal_length 4.7, :petal_width 1.4, :variety "Versicolor"}
    {:sepal_length 6.4, :sepal_width 3.2, :petal_length 4.5, :petal_width 1.5, :variety "Versicolor"}
    {:sepal_length 6.9, :sepal_width 3.1, :petal_length 4.9, :petal_width 1.5, :variety "Versicolor"}
    {:sepal_length 5.5, :sepal_width 2.3, :petal_length 4, :petal_width 1.3, :variety "Versicolor"}
    {:sepal_length 6.5, :sepal_width 2.8, :petal_length 4.6, :petal_width 1.5, :variety "Versicolor"}
    {:sepal_length 5.7, :sepal_width 2.8, :petal_length 4.5, :petal_width 1.3, :variety "Versicolor"}
    {:sepal_length 6.3, :sepal_width 3.3, :petal_length 4.7, :petal_width 1.6, :variety "Versicolor"}
    {:sepal_length 4.9, :sepal_width 2.4, :petal_length 3.3, :petal_width 1, :variety "Versicolor"}
    {:sepal_length 6.6, :sepal_width 2.9, :petal_length 4.6, :petal_width 1.3, :variety "Versicolor"}
    {:sepal_length 5.2, :sepal_width 2.7, :petal_length 3.9, :petal_width 1.4, :variety "Versicolor"}
    {:sepal_length 5, :sepal_width 2, :petal_length 3.5, :petal_width 1, :variety "Versicolor"}
    {:sepal_length 5.9, :sepal_width 3, :petal_length 4.2, :petal_width 1.5, :variety "Versicolor"}
    {:sepal_length 6, :sepal_width 2.2, :petal_length 4, :petal_width 1, :variety "Versicolor"}
    {:sepal_length 6.1, :sepal_width 2.9, :petal_length 4.7, :petal_width 1.4, :variety "Versicolor"}
    {:sepal_length 5.6, :sepal_width 2.9, :petal_length 3.6, :petal_width 1.3, :variety "Versicolor"}
    {:sepal_length 6.7, :sepal_width 3.1, :petal_length 4.4, :petal_width 1.4, :variety "Versicolor"}
    {:sepal_length 5.6, :sepal_width 3, :petal_length 4.5, :petal_width 1.5, :variety "Versicolor"}
    {:sepal_length 5.8, :sepal_width 2.7, :petal_length 4.1, :petal_width 1, :variety "Versicolor"}
    {:sepal_length 6.2, :sepal_width 2.2, :petal_length 4.5, :petal_width 1.5, :variety "Versicolor"}
    {:sepal_length 5.6, :sepal_width 2.5, :petal_length 3.9, :petal_width 1.1, :variety "Versicolor"}
    {:sepal_length 5.9, :sepal_width 3.2, :petal_length 4.8, :petal_width 1.8, :variety "Versicolor"}
    {:sepal_length 6.1, :sepal_width 2.8, :petal_length 4, :petal_width 1.3, :variety "Versicolor"}
    {:sepal_length 6.3, :sepal_width 2.5, :petal_length 4.9, :petal_width 1.5, :variety "Versicolor"}
    {:sepal_length 6.1, :sepal_width 2.8, :petal_length 4.7, :petal_width 1.2, :variety "Versicolor"}
    {:sepal_length 6.4, :sepal_width 2.9, :petal_length 4.3, :petal_width 1.3, :variety "Versicolor"}
    {:sepal_length 6.6, :sepal_width 3, :petal_length 4.4, :petal_width 1.4, :variety "Versicolor"}
    {:sepal_length 6.8, :sepal_width 2.8, :petal_length 4.8, :petal_width 1.4, :variety "Versicolor"}
    {:sepal_length 6.7, :sepal_width 3, :petal_length 5, :petal_width 1.7, :variety "Versicolor"}
    {:sepal_length 6, :sepal_width 2.9, :petal_length 4.5, :petal_width 1.5, :variety "Versicolor"}
    {:sepal_length 5.7, :sepal_width 2.6, :petal_length 3.5, :petal_width 1, :variety "Versicolor"}
    {:sepal_length 5.5, :sepal_width 2.4, :petal_length 3.8, :petal_width 1.1, :variety "Versicolor"}
    {:sepal_length 5.5, :sepal_width 2.4, :petal_length 3.7, :petal_width 1, :variety "Versicolor"}
    {:sepal_length 5.8, :sepal_width 2.7, :petal_length 3.9, :petal_width 1.2, :variety "Versicolor"}
    {:sepal_length 6, :sepal_width 2.7, :petal_length 5.1, :petal_width 1.6, :variety "Versicolor"}
    {:sepal_length 5.4, :sepal_width 3, :petal_length 4.5, :petal_width 1.5, :variety "Versicolor"}
    {:sepal_length 6, :sepal_width 3.4, :petal_length 4.5, :petal_width 1.6, :variety "Versicolor"}
    {:sepal_length 6.7, :sepal_width 3.1, :petal_length 4.7, :petal_width 1.5, :variety "Versicolor"}
    {:sepal_length 6.3, :sepal_width 2.3, :petal_length 4.4, :petal_width 1.3, :variety "Versicolor"}
    {:sepal_length 5.6, :sepal_width 3, :petal_length 4.1, :petal_width 1.3, :variety "Versicolor"}
    {:sepal_length 5.5, :sepal_width 2.5, :petal_length 4, :petal_width 1.3, :variety "Versicolor"}
    {:sepal_length 5.5, :sepal_width 2.6, :petal_length 4.4, :petal_width 1.2, :variety "Versicolor"}
    {:sepal_length 6.1, :sepal_width 3, :petal_length 4.6, :petal_width 1.4, :variety "Versicolor"}
    {:sepal_length 5.8, :sepal_width 2.6, :petal_length 4, :petal_width 1.2, :variety "Versicolor"}
    {:sepal_length 5, :sepal_width 2.3, :petal_length 3.3, :petal_width 1, :variety "Versicolor"}
    {:sepal_length 5.6, :sepal_width 2.7, :petal_length 4.2, :petal_width 1.3, :variety "Versicolor"}
    {:sepal_length 5.7, :sepal_width 3, :petal_length 4.2, :petal_width 1.2, :variety "Versicolor"}
    {:sepal_length 5.7, :sepal_width 2.9, :petal_length 4.2, :petal_width 1.3, :variety "Versicolor"}
    {:sepal_length 6.2, :sepal_width 2.9, :petal_length 4.3, :petal_width 1.3, :variety "Versicolor"}
    {:sepal_length 5.1, :sepal_width 2.5, :petal_length 3, :petal_width 1.1, :variety "Versicolor"}
    {:sepal_length 5.7, :sepal_width 2.8, :petal_length 4.1, :petal_width 1.3, :variety "Versicolor"}
    {:sepal_length 6.3, :sepal_width 3.3, :petal_length 6, :petal_width 2.5, :variety "Virginica"}
    {:sepal_length 5.8, :sepal_width 2.7, :petal_length 5.1, :petal_width 1.9, :variety "Virginica"}
    {:sepal_length 7.1, :sepal_width 3, :petal_length 5.9, :petal_width 2.1, :variety "Virginica"}
    {:sepal_length 6.3, :sepal_width 2.9, :petal_length 5.6, :petal_width 1.8, :variety "Virginica"}
    {:sepal_length 6.5, :sepal_width 3, :petal_length 5.8, :petal_width 2.2, :variety "Virginica"}
    {:sepal_length 7.6, :sepal_width 3, :petal_length 6.6, :petal_width 2.1, :variety "Virginica"}
    {:sepal_length 4.9, :sepal_width 2.5, :petal_length 4.5, :petal_width 1.7, :variety "Virginica"}
    {:sepal_length 7.3, :sepal_width 2.9, :petal_length 6.3, :petal_width 1.8, :variety "Virginica"}
    {:sepal_length 6.7, :sepal_width 2.5, :petal_length 5.8, :petal_width 1.8, :variety "Virginica"}
    {:sepal_length 7.2, :sepal_width 3.6, :petal_length 6.1, :petal_width 2.5, :variety "Virginica"}
    {:sepal_length 6.5, :sepal_width 3.2, :petal_length 5.1, :petal_width 2, :variety "Virginica"}
    {:sepal_length 6.4, :sepal_width 2.7, :petal_length 5.3, :petal_width 1.9, :variety "Virginica"}
    {:sepal_length 6.8, :sepal_width 3, :petal_length 5.5, :petal_width 2.1, :variety "Virginica"}
    {:sepal_length 5.7, :sepal_width 2.5, :petal_length 5, :petal_width 2, :variety "Virginica"}
    {:sepal_length 5.8, :sepal_width 2.8, :petal_length 5.1, :petal_width 2.4, :variety "Virginica"}
    {:sepal_length 6.4, :sepal_width 3.2, :petal_length 5.3, :petal_width 2.3, :variety "Virginica"}
    {:sepal_length 6.5, :sepal_width 3, :petal_length 5.5, :petal_width 1.8, :variety "Virginica"}
    {:sepal_length 7.7, :sepal_width 3.8, :petal_length 6.7, :petal_width 2.2, :variety "Virginica"}
    {:sepal_length 7.7, :sepal_width 2.6, :petal_length 6.9, :petal_width 2.3, :variety "Virginica"}
    {:sepal_length 6, :sepal_width 2.2, :petal_length 5, :petal_width 1.5, :variety "Virginica"}
    {:sepal_length 6.9, :sepal_width 3.2, :petal_length 5.7, :petal_width 2.3, :variety "Virginica"}
    {:sepal_length 5.6, :sepal_width 2.8, :petal_length 4.9, :petal_width 2, :variety "Virginica"}
    {:sepal_length 7.7, :sepal_width 2.8, :petal_length 6.7, :petal_width 2, :variety "Virginica"}
    {:sepal_length 6.3, :sepal_width 2.7, :petal_length 4.9, :petal_width 1.8, :variety "Virginica"}
    {:sepal_length 6.7, :sepal_width 3.3, :petal_length 5.7, :petal_width 2.1, :variety "Virginica"}
    {:sepal_length 7.2, :sepal_width 3.2, :petal_length 6, :petal_width 1.8, :variety "Virginica"}
    {:sepal_length 6.2, :sepal_width 2.8, :petal_length 4.8, :petal_width 1.8, :variety "Virginica"}
    {:sepal_length 6.1, :sepal_width 3, :petal_length 4.9, :petal_width 1.8, :variety "Virginica"}
    {:sepal_length 6.4, :sepal_width 2.8, :petal_length 5.6, :petal_width 2.1, :variety "Virginica"}
    {:sepal_length 7.2, :sepal_width 3, :petal_length 5.8, :petal_width 1.6, :variety "Virginica"}
    {:sepal_length 7.4, :sepal_width 2.8, :petal_length 6.1, :petal_width 1.9, :variety "Virginica"}
    {:sepal_length 7.9, :sepal_width 3.8, :petal_length 6.4, :petal_width 2, :variety "Virginica"}
    {:sepal_length 6.4, :sepal_width 2.8, :petal_length 5.6, :petal_width 2.2, :variety "Virginica"}
    {:sepal_length 6.3, :sepal_width 2.8, :petal_length 5.1, :petal_width 1.5, :variety "Virginica"}
    {:sepal_length 6.1, :sepal_width 2.6, :petal_length 5.6, :petal_width 1.4, :variety "Virginica"}
    {:sepal_length 7.7, :sepal_width 3, :petal_length 6.1, :petal_width 2.3, :variety "Virginica"}
    {:sepal_length 6.3, :sepal_width 3.4, :petal_length 5.6, :petal_width 2.4, :variety "Virginica"}
    {:sepal_length 6.4, :sepal_width 3.1, :petal_length 5.5, :petal_width 1.8, :variety "Virginica"}
    {:sepal_length 6, :sepal_width 3, :petal_length 4.8, :petal_width 1.8, :variety "Virginica"}
    {:sepal_length 6.9, :sepal_width 3.1, :petal_length 5.4, :petal_width 2.1, :variety "Virginica"}
    {:sepal_length 6.7, :sepal_width 3.1, :petal_length 5.6, :petal_width 2.4, :variety "Virginica"}
    {:sepal_length 6.9, :sepal_width 3.1, :petal_length 5.1, :petal_width 2.3, :variety "Virginica"}
    {:sepal_length 5.8, :sepal_width 2.7, :petal_length 5.1, :petal_width 1.9, :variety "Virginica"}
    {:sepal_length 6.8, :sepal_width 3.2, :petal_length 5.9, :petal_width 2.3, :variety "Virginica"}
    {:sepal_length 6.7, :sepal_width 3.3, :petal_length 5.7, :petal_width 2.5, :variety "Virginica"}
    {:sepal_length 6.7, :sepal_width 3, :petal_length 5.2, :petal_width 2.3, :variety "Virginica"}
    {:sepal_length 6.3, :sepal_width 2.5, :petal_length 5, :petal_width 1.9, :variety "Virginica"}
    {:sepal_length 6.5, :sepal_width 3, :petal_length 5.2, :petal_width 2, :variety "Virginica"}
    {:sepal_length 6.2, :sepal_width 3.4, :petal_length 5.4, :petal_width 2.3, :variety "Virginica"}
    {:sepal_length 5.9, :sepal_width 3, :petal_length 5.1, :petal_width 1.8, :variety "Virginica"})
   })

(def data-color "#e09f3e")            ;TODO elsewhere

(defn block
  [[name data]]
  {:type name
   :colour data-color
   :message0 name
   :output "data"})

(defn blockdefs
  []
  (map block datasets))

(defn toolbox
  []
  (mapv (fn [name] [:block name]) (keys datasets)))
