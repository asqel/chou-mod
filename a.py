colors = [
"White", 
"Light_Gray",
"Gray", 
"Black",
"Brown", 
"Red",
"Orange", 
"Yellow",
"Lime", 
"Green",
"Cyan", 
"Light_Blue",
"Blue", 
"Purple",
"Magenta", 
"Pink",
]

for i in colors:
	c = i.lower()
	print(f"""public static final Item {c.upper()}_WATER_BOTTLE = register("{c}_water_bottle", () -> {{return new colored_bottle("{c}");}});""")

for i in colors:
	c = i.upper()
	print(f"output.accept(ModItems.{c}_WATER_BOTTLE);")
